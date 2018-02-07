package com.pointwest.pls.constant;

public interface SqlConstants {
	String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	String NETWORK_ADDRESS = "localhost";
	String DATABASE_SCHEMA = "plsdb";
	String DATABASE_NETWORK = "jdbc:mysql://" + NETWORK_ADDRESS + ":3306/" + DATABASE_SCHEMA;
	String DATABASE_USERNAME = "david.abayon";
	String DATABASE_PASSWORD = "password123";

	int QUERY_TIMEOUT = 180;
	String EMP_ROLE = "emp_role";
	String EMP_ID = "emp_id";
	String EMP_FIRST_NAME = "emp_first_name";
	String EMP_LAST_NAME = "emp_last_name";
	String SEAT_ID = "seat_id";
	String SEAT_NAME = "seat_name";
	String SEAT_LOCAL_NUMBER = "seat_local_number";
	String EMP_SHIFT = "emp_shift";
	String PROJ_NAME = "proj_name";

	String SELECT_STATEMENT_LOGIN = "SELECT emp_first_name, emp_last_name, emp_role ";
	String FROM_STATEMENT_LOGIN = "FROM employee ";
	String WHERE_STATEMENT_LOGIN = "WHERE emp_username = ? AND emp_password = BINARY ?";

	// Search Employee queries
	String SELECT_STATEMENT_SEARCH_EMPLOYEE = "SELECT e.emp_id, " + "e.emp_first_name, " + "e.emp_last_name, "
			+ "CONCAT(s.bldg_id, s.seat_floor_number, 'F', s.seat_quadrant,  s.seat_column_number, '-', s.seat_row_number) AS seat_name, "
			+ "(CASE WHEN s.seat_local_number = '' THEN 'N/A' ELSE s.seat_local_number END) as seat_local_number, "
			+ "e.emp_shift, "
			+ "GROUP_CONCAT(DISTINCT(CASE WHEN p.proj_name = 'Project Never Exist' THEN NULL ELSE p.proj_name END) SEPARATOR ', ') AS proj_name ";
	String FROM_STATEMENT_SEARCH_EMPLOYEE = "FROM employee e " + "LEFT JOIN employee_seat es ON e.emp_id = es.emp_id "
			+ "LEFT JOIN seat s ON es.seat_id = s.seat_id " + "LEFT JOIN employee_project ep ON e.emp_id = ep.emp_id "
			+ "LEFT JOIN project p ON ep.proj_alias = p.proj_alias ";
	String WHERE_STATEMENT_SEARCH_BY_ID_PARTIAL = "WHERE e.emp_id LIKE ? ";
	String WHERE_STATEMENT_SEARCH_BY_NAME_PARTIAL = "WHERE CONCAT(e.emp_first_name, ' ', e.emp_last_name) LIKE ? ";
	String WHERE_STATEMENT_SEARCH_BY_PROJECT_PARTIAL = "WHERE p.proj_name LIKE ? ";
	String GROUP_BY_ORDER_BY_EMPLOYEE_ID = "GROUP BY e.emp_id, seat_name ORDER BY e.emp_id";

	// View Seatplan queries
	String SELECT_STATEMENT_VIEW_SEATPLAN = "SELECT s.seat_id, "
			+ "CONCAT(s.bldg_id, s.seat_floor_number, 'F', s.seat_quadrant, s.seat_row_number, '-', s.seat_column_number) AS seat_name, "
			+ "(CASE WHEN e.emp_first_name IS NULL THEN 'Vacant' ELSE e.emp_first_name END) as emp_first_name, "
			+ "(CASE WHEN e.emp_last_name IS NULL THEN 'seat' ELSE e.emp_last_name END) as emp_last_name, "
			+ "(CASE WHEN s.seat_local_number = '' THEN 'N/A' ELSE CONCAT('Local #', s.seat_local_number) END) as seat_local_number ";
	String FROM_STATEMENT_VIEW_SEATPLAN = "FROM seat s " + "LEFT JOIN employee_seat es ON s.seat_id = es.seat_id "
			+ "LEFT JOIN employee e ON es.emp_id = e.emp_id " + "LEFT JOIN employee_project ep ON ep.emp_id = e.emp_id "
			+ "LEFT JOIN project p ON p.proj_alias = ep.proj_alias ";
	String WHERE_STATEMENT_VIEW_BY_LOCATION_FLOOR = "WHERE s.bldg_id = ? AND s.seat_floor_number = ?";
	String WHERE_STATEMENT_VIEW_BY_QUADRANT = "WHERE s.bldg_id = ? AND s.seat_floor_number = ? AND s.seat_quadrant = ?";
	String GROUP_BY_ORDER_BY_SEAT_ID = "GROUP BY s.seat_id ORDER BY s.seat_id";

	String SELECT_STATEMENT_VIEW_SEATPLAN_QUADRANT = "SELECT s.seat_id, "
			+ "(CASE WHEN e.emp_first_name IS NULL THEN 'Vacant' ELSE e.emp_first_name END) as emp_first_name, "
			+ "(CASE WHEN e.emp_last_name IS NULL THEN 'seat' ELSE e.emp_last_name END) as emp_last_name, "
			+ "(CASE WHEN s.seat_local_number = '' THEN 'N/A' ELSE CONCAT('Local #', s.seat_local_number) END) as seat_local_number ";
	String QUERY_STATEMENT_VIEW_SEATPLAN_QUADRANT = "SELECT s.seat_id, "
			+ "CONCAT(s.bldg_id, s.seat_floor_number, 'F', s.seat_quadrant, s.seat_row_number, '-', s.seat_column_number) AS seat_name, "
			+ "se. emp_first_name, " + "se.emp_last_name, " + "se.seat_local_number " + "FROM seat s " + "LEFT JOIN ("
			+ SELECT_STATEMENT_VIEW_SEATPLAN_QUADRANT + FROM_STATEMENT_VIEW_SEATPLAN + WHERE_STATEMENT_VIEW_BY_QUADRANT
			+ GROUP_BY_ORDER_BY_SEAT_ID + ") se ON s.seat_id = se.seat_id " + "WHERE s.bldg_id = ? "
			+ "AND s.seat_floor_number = ?";

	String SELECT_STATEMENT_VIEW_SEATPLAN_EMPLOYEE = "SELECT s.seat_id, "
			+ "(CASE WHEN e.emp_first_name IS NULL THEN 'Vacant' ELSE e.emp_first_name END) as emp_first_name, "
			+ "(CASE WHEN e.emp_last_name IS NULL THEN 'seat' ELSE e.emp_last_name END) as emp_last_name, "
			+ "(CASE WHEN s.seat_local_number = '' THEN 'N/A' ELSE CONCAT('Local #', s.seat_local_number) END) as seat_local_number ";
	String QUERY_STATEMENT_VIEW_SEATPLAN_EMPLOYEE_BY_ID = "SELECT s.seat_id, s.bldg_id, s.seat_floor_number, "
			+ "CONCAT(s.bldg_id, s.seat_floor_number, 'F', s.seat_quadrant, s.seat_row_number, '-', s.seat_column_number) AS seat_name, "
			+ "se. emp_first_name, " + "se.emp_last_name, " + "se.seat_local_number " + "FROM seat s " + "LEFT JOIN ("
			+ SELECT_STATEMENT_VIEW_SEATPLAN_EMPLOYEE + FROM_STATEMENT_VIEW_SEATPLAN
			+ WHERE_STATEMENT_SEARCH_BY_ID_PARTIAL + GROUP_BY_ORDER_BY_SEAT_ID + ") se ON s.seat_id = se.seat_id "
			+ "WHERE s.bldg_id = ? " + "AND s.seat_floor_number = ?";
}