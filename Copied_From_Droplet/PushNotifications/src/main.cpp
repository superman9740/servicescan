//
// main.cpp
//
// Copyright (c) 2013 MacGeneration. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
// conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
// of conditions and the following disclaimer in the documentation and/or other materials
// provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS "AS IS" AND ANY EXPRESS OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
// FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
// CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
// ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
// NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
// ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//


#include "global.hpp"
#include "MMGAPNSConnection.hpp"
#include "MMGDevice.hpp"
#include "MMGIOSPayload.hpp"
#include "MMGTools.hpp"
#include <vector>
#include <cstdlib>
#include <mysql.h>


#ifdef __APPLE__
#pragma clang diagnostic ignored "-Wdeprecated-declarations"
#pragma gcc diagnostic ignored "-Wdeprecated-declarations"
#endif


static size_t get_devices_list(std::vector<MMGDevice*>& vec)
{

	const unsigned int badge = 1;
	MMGDevice* device = new MMGDevice("99b2e3cac0f2e732aba269a149fbad52ff89f794f3370b1679d06b1975193601", badge);
	
        vec.push_back(device);
	
	return vec.size();


    /* MySQL bridging example
     
     need to link with libmysqlclient or libmysqclient_r (multithread)
     also #include <mysql/mysql.h>
     
     let's assume we have a simple table like that
     --------------
     |   devices  |
     |------------|
     |token       |
     |unread_count|
     --------------
     */
/*    
    mysql_library_init(0, NULL, NULL);

    printf("Inside get_devices_list:  app_name is %s\n",app_name.c_str());
    
    // Connect to db
    MYSQL* conn = mysql_init(NULL);
    if (NULL == conn)
    {
        MMG_ERRLOG("[!] Not enough memory to allocate the MySQL connection\n");
        return 0;
    }
    mysql_options(conn, MYSQL_SET_CHARSET_NAME, "utf8");
    if (!mysql_real_connect(conn, "memlnx30.tnb.com", "usrodic", "pass123", "apps", 0, NULL, 0))
    {
        MMG_ERRLOG("[!] mysql_real_connect: %s\n", mysql_error(conn));
        mysql_close(conn);
        return 0;
    }
    
    
    
	 std::string  query = "SELECT * from apps.cas_installed_apps where app_name = '" + app_name + "'";
	printf("Query value is:  %s\n",query.c_str());

        	 
   if (mysql_query(conn, query.c_str()) != 0)
    {
        MMG_ERRLOG("[!] mysql_query: %s\n", mysql_error(conn));
        mysql_close(conn);
        return 0;
    }
    MYSQL_RES* res = mysql_use_result(conn);
    if (NULL == res)
    {
        MMG_ERRLOG("[!] mysql_use_result: %s\n", mysql_error(conn));
        mysql_close(conn);
        return 0;
    }
    
    // Build the list of MMGDevices
    MYSQL_ROW row = NULL;
    while ((row = mysql_fetch_row(res)))
    {
        std::string token = row[1];
	MMGDevice* device = new MMGDevice(token, 1);
        printf("row value: %s\n",row[1]);

	vec.push_back(device);
    }
    
    mysql_free_result(res);
    mysql_close(conn);
    
    return vec.size();
*/


}

int main(int argc, char* argv[])
{
	std::string device_token = argv[1];
	std::string qrCode = argv[2];
	printf("device token:  %s qrCode:  %s\n", device_token.c_str(),qrCode.c_str());

	// SLL init only once
	SSL_load_error_strings();
	SSL_library_init();

	// Get a list of devices
//	std::vector<MMGDevice*> devices;
	//get_devices_list(devices);

	// Create a payload object
	MMGIOSPayload payload("Incoming service request", qrCode, 1, "sound.caf");
	const unsigned int badge = 1;
        MMGDevice* device = new MMGDevice(device_token.c_str(), badge);


	
	

	// Send the payload
	//std::vector<MMGDevice*>::iterator i = devices.begin();
	//std::vector<MMGDevice*>::iterator end = devices.end();
//	for(;i != end;i++)
//	{ 
	
		
		// Update payload badge number to reflect device's one
		payload.SetBadgeNumber(1);
		
		// Send payload to the device

               

	      MMGAPNSConnection connection(MMG_APNS_CA_PATH, MMG_APNS_CERT_PATH, MMG_APNS_PRIVATEKEY_PATH, "Push", true)
;
		 if(connection.OpenConnection() != MMGNoError)
                	return EXIT_FAILURE;
		printf("Connected to Sandbox APNS.\n");
		connection.SendPayloadToDevice(payload, *device);

		connection.CloseConnection();
		printf("Sending Sandbox payload...\n");
//	}

	// Free up memory
	


	return EXIT_SUCCESS;
}
