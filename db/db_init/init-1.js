/**
* This program implements database batch workflow 
* ./input/* provide csv data input files
* ./output/* archives intermediary treatment sql and data parsing file: useful for trouble-shooting
**/

var INPUT_PATH = process.env.INPUT_PATH;
var OUTPUT_PATH = process.env.OUTPUT_PATH;
var TOOLSET_PATH = process.env.TOOLSET_PATH;

var taskRunner = require(TOOLSET_PATH+'/taskRunner.js');

var right_parser_callback = function(record) {
	var profile_right = {'ProfileCode':record[0], 'ProfileLabel':record[1], 'RightCode':record[2], 'RightLabel':record[3]};
	return profile_right;
}

var right_sql_callback = function(dataStructure) {
	var sqlScript = '';
	dataStructure.map(function(item){
		var table1 = "profile";
		var table2 = "access_right";
		sqlScript	+= "INSERT INTO "
					+ table1 
					+ " (code, label) "
					+ " SELECT \'"+ item.ProfileCode + "\',\'" + item.ProfileLabel + "\'"
					+ " WHERE NOT EXISTS ( SELECT 1 FROM " + table1 + " where code = \'" + item.ProfileCode + "\' and label = \'" + item.ProfileLabel + "\')"
					+ ";\n";
		sqlScript	+= "INSERT INTO "
					+ table2 
					+ " (code, label) "
					+ " SELECT \'"+ item.RightCode + "\',\'" + item.RightLabel + "\'"
					+ " WHERE NOT EXISTS ( SELECT 1 FROM " + table2 + " where code = \'" + item.RightCode + "\' and label = \'" + item.RightLabel + "\')"
					+ ";\n";
	});
	return sqlScript;
}

var user_parser_callback = function(record) {
	var profile_user = {'ProfileCode':record[0], 'ProfileLabel':record[1], 'UserLogin':record[2], 'UserPassword':record[3]};
	return profile_user;
}

var user_sql_callback = function(dataStructure) {
	var sqlScript = '';
	dataStructure.map(function(item){
		var table = "app_user";
		sqlScript	+= "INSERT INTO "
					+  table 
					+  " (login, password) "
					+  " SELECT \'"+ item.UserLogin + "\',\'" + item.UserPassword + "\'"
					+  " WHERE NOT EXISTS ( SELECT 1 FROM " + table + " where login = \'" + item.UserLogin + "\' and password = \'" + item.UserPassword + "\')"
					+  ";\n";
	});
	return sqlScript;
}

var robot_parser_callback = function(record) {
	var robot = {'RobotLabel':record[0], 'RobotDescription':record[1], 'RobotPrice':record[2], 'RobotQuantity':record[3]};
	return robot;
}

var robot_sql_callback = function(dataStructure) {
	var sqlScript = '';
	dataStructure.map(function(item){
		var table = "robot";
		sqlScript	+= "INSERT INTO "
					+  table 
					+  " (label, description, price, qty) "
					+  " SELECT \'"+ item.RobotLabel + "\',\'" + item.RobotDescription + "\'," + item.RobotPrice + "," + item.RobotQuantity + ""
					+  " WHERE NOT EXISTS ( SELECT 1 FROM " + table + " where label = \'" + item.RobotLabel + "\' and description = \'" + item.RobotDescription + "\')"
					+  ";\n";
	});
	return sqlScript;
}

/*
 * We load Profiles and Rights
 * File's header
 * ProfileCode,ProfileLabel,RightCode,RightCode
 */
taskRunner.parseAndUpsert('Profiles and Rights Creation', 
							INPUT_PATH+'/profile_rights.csv', 
							right_parser_callback, 
							OUTPUT_PATH+'/profile_rights.json', 
							right_sql_callback, 
							OUTPUT_PATH+"rights_and_profiles.sql");

/*
 * We load Profiles and Rights
 * File's header
 * ProfileCode,ProfileLabel,UserLogin,UserPassword
 */
taskRunner.parseAndUpsert('Users Creation', 
							INPUT_PATH+'/profile_users.csv', 
							user_parser_callback, 
							OUTPUT_PATH+'/profile_users.json', 
							user_sql_callback, 
							OUTPUT_PATH+"users.sql");

/*
 * We load Robots
 * File's header
 * RobotLabel,RobotDescription,RobotPrice,RobotQuantity
 */
taskRunner.parseAndUpsert('Robots Creation', 
							INPUT_PATH+'/robots.csv', 
							robot_parser_callback, 
							OUTPUT_PATH+'/robots.json', 
							robot_sql_callback, 
							OUTPUT_PATH+"robots.sql");