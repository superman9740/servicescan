g++ MMGAPNSConnection.cpp MMGSSLConnection.cpp MMGDevice.cpp MMGTools.cpp MMGPayload.cpp  MMGIOSPayload.cpp main.cpp -I/usr/local/mysql/include /usr/local/mysql/lib/libmysqlclient.dylib  -lssl -lcrypto      -std=c++0x -o MacGPusher