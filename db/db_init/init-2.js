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
		var table1 = "profile_right";
		var table2 = "profile";
		var table3 = "access_right";
		sqlScript	+= "INSERT INTO "
					+  table1 
					+  " (profile_id, right_id) "
					+  " SELECT " + table2 + ".id, " + table3 + ".id "
					+  " FROM " + table2 + "," + table3 + " "
					+  " WHERE " + table2 + ".code = \'" + item.ProfileCode +"\' and " + table3 + ".code = \'" + item.RightCode +"\'"
					+  " AND NOT EXISTS ( SELECT 1 FROM " + table1 + " where profile_id = ( SELECT id from " + table2 + " where code = \'" + item.ProfileCode +"\' ) and right_id = ( SELECT id from " + table3 + " where code = \'" +item.RightCode +"\' ) )"
					+  ";\n";
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
		var table1 = "app_user";
		var table2 = "profile";
		sqlScript	+= "UPDATE "
					+  table1 
					+  " SET profile_id = ( SELECT id from " + table2 +" where code = \'" + item.ProfileCode + "\' ) "
					+  " WHERE login = \'" + item.UserLogin + "\'"
					+  ";\n";
	});
	return sqlScript;
}

/*
 * We load Profiles and Rights Mapping
 * File's header
 * ProfileCode,ProfileLabel,RightCode,RightCode
 */
taskRunner.parseAndUpsert('Profiles and Rights Mapping', 
							INPUT_PATH+'/profile_rights.csv', 
							right_parser_callback, 
							null, 
							right_sql_callback, 
							OUTPUT_PATH+"rights_and_profiles_mapping.sql");

/*
 * We load Profiles and Users Mapping
 * File's header
 * ProfileCode,ProfileLabel,UserLogin,UserPassword
 */
taskRunner.parseAndUpsert('Profiles and Users Mapping', 
							INPUT_PATH+'/profile_users.csv', 
							user_parser_callback, 
							null, 
							user_sql_callback, 
							OUTPUT_PATH+"profiles_and_users_mapping.sql");