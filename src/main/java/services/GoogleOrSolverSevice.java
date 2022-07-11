package services;

import java.util.logging.Logger;

import com.google.ortools.Loader;
import com.google.ortools.constraintsolver.Assignment;
import com.google.ortools.constraintsolver.FirstSolutionStrategy;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.RoutingDimension;
import com.google.ortools.constraintsolver.RoutingIndexManager;
import com.google.ortools.constraintsolver.RoutingModel;
import com.google.ortools.constraintsolver.RoutingSearchParameters;
import com.google.ortools.constraintsolver.main;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class GoogleOrSolverSevice
{
	private static final Logger logger = Logger.getLogger(GoogleOrSolverSevice.class.getName());

	public static void generateRoutes(DataModel data)
	{
		Loader.loadNativeLibraries();
		// Create Routing Index Manager
		// [START index_manager]
		RoutingIndexManager manager = new RoutingIndexManager(data.getTimeMatrix().length, data.getVehicleNumber(), data.getDepot());
		// [END index_manager]

		// Create Routing Model.
		// [START routing_model]
		RoutingModel routing = new RoutingModel(manager);
		// [END routing_model]

		// Create and register a transit callback.
		// [START transit_callback]
		final int transitCallbackIndex = routing.registerTransitCallback((long fromIndex, long toIndex) -> {
			// Convert from routing variable Index to user NodeIndex.
			int fromNode = manager.indexToNode(fromIndex);
			int toNode = manager.indexToNode(toIndex);
			return data.timeMatrix[fromNode][toNode];
		});
		// [END transit_callback]

		// Define cost of each arc.
		// [START arc_cost]
		routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);
		// [END arc_cost]

		// Add Time constraint.
		// [START time_constraint]
		routing.addDimension(transitCallbackIndex, // transit callback
			30, // allow waiting time
			30, // vehicle maximum capacities
			false, // start cumul to zero
			"Time");
		RoutingDimension timeDimension = routing.getMutableDimension("Time");
		// Add time window constraints for each location except depot.
		for (int i = 1; i < data.getTimeWindows().length; ++i)
		{
			long index = manager.nodeToIndex(i);
			timeDimension.cumulVar(index).setRange(data.getTimeWindows()[i][0], data.getTimeWindows()[i][1]);
		}
		// Add time window constraints for each vehicle start node.
		for (int i = 0; i < data.getVehicleNumber(); ++i)
		{
			long index = routing.start(i);
			timeDimension.cumulVar(index).setRange(data.getTimeWindows()[0][0], data.getTimeWindows()[0][1]);
		}
		// [END time_constraint]

		// Instantiate route start and end times to produce feasible times.
		// [START depot_start_end_times]
		for (int i = 0; i < data.getVehicleNumber(); ++i)
		{
			routing.addVariableMinimizedByFinalizer(timeDimension.cumulVar(routing.start(i)));
			routing.addVariableMinimizedByFinalizer(timeDimension.cumulVar(routing.end(i)));
		}
		// [END depot_start_end_times]

		// Setting first solution heuristic.
		// [START parameters]
		RoutingSearchParameters searchParameters = main.defaultRoutingSearchParameters().toBuilder()
			.setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC).build();
		// [END parameters]

		// Solve the problem.
		// [START solve]
		Assignment solution = routing.solveWithParameters(searchParameters);
		// [END solve]

		// Print solution on console.
		// [START print_solution]
		printSolution(data, routing, manager, solution);
		// [END print_solution]
	}

	public static void printSolution(DataModel data, RoutingModel routing, RoutingIndexManager manager, Assignment solution)
	{
		// Solution cost.
		logger.info("Objective : " + solution.objectiveValue());
		// Inspect solution.
		RoutingDimension timeDimension = routing.getMutableDimension("Time");
		long totalTime = 0;
		for (int i = 0; i < data.getVehicleNumber(); ++i)
		{
			long index = routing.start(i);
			logger.info("Route for Vehicle " + i + ":");
			String route = "";
			while (!routing.isEnd(index))
			{
				IntVar timeVar = timeDimension.cumulVar(index);
				route += manager.indexToNode(index) + " Time(" + solution.min(timeVar) + "," + solution.max(timeVar) + ") -> ";
				index = solution.value(routing.nextVar(index));
			}
			IntVar timeVar = timeDimension.cumulVar(index);
			route += manager.indexToNode(index) + " Time(" + solution.min(timeVar) + "," + solution.max(timeVar) + ")";
			logger.info(route);
			logger.info("Time of the route: " + solution.min(timeVar) + "min");
			totalTime += solution.min(timeVar);
		}
		logger.info("Total time of all routes: " + totalTime + "min");
	}
}
