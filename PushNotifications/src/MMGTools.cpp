//
// MMGTools.cpp
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


#include "MMGTools.hpp"
#include <cstdio>
#include <cstring>
#include <sstream>


#define INT_DIGITS 19


int MMGTools::StringToInteger(const std::string& str)
{
#ifdef __clang__
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunused-value"
#endif
	char* s = const_cast<char*>(str.c_str());
	int res = 0, n = 1;
	unsigned int c;
	if (*s == '-')
	{
		n = -1;
		*s++;
	}
	while ((unsigned int)(c = (unsigned int)*s++ - 48) < 10u)
		res = res * 10 + (int)c;
	return res * n;
#ifdef __clang__
#pragma clang diagnostic pop
#endif
}

unsigned int MMGTools::StringToUnsignedInteger(const std::string& str)
{
	const char* s = str.c_str();
	unsigned int res = 0;
	unsigned int c;
	while ((unsigned int)(c = (unsigned int)*s++ - 48) < 10u)
		res = res * 10 + c;
	return res;
}

const std::string MMGTools::IntegerToString(int i)
{
	static char buf[INT_DIGITS + 2];
	char* p = buf + INT_DIGITS + 1;
	if (i >= 0)
	{
		do
		{
			*--p = 48 + (i % 10);
			i /= 10;
		} while (i != 0);
		return std::string(p);
	}
	else
	{
		do
		{
			*--p = 48 - (i % 10);
			i /= 10;
		} while (i != 0);
		*--p = '-';
	}
	return std::string(p);
}

const std::string MMGTools::UnsignedIntegerToString(unsigned int i)
{
	static char buf[INT_DIGITS + 2];
	char* p = buf + INT_DIGITS + 1;
	do
	{
		*--p = 48 + (i % 10);
		i /= 10;
	} while (i != 0);
	return std::string(p);
}

const std::string MMGTools::FloatToString(const float f)
{
	char array[32] = {0x00};
	sprintf(array, "%f", f);
	return std::string(array);
}

void MMGTools::StringToVector(const std::string& str, std::vector<std::string>& vec, const char* sep)
{
	char* cStr = const_cast<char*>(str.c_str());
	char* p = strtok(cStr, sep);
	while (p)
	{
		vec.push_back(p);
		p = strtok(NULL, sep);
	}
}

bool MMGTools::StringReplace(std::string& str, const std::string& from, const std::string& to)
{
	const std::string::size_type startPos = str.find(from);
	if (startPos == std::string::npos)
		return false;
	str.replace(startPos, from.length(), to);
	return true;
}

void MMGTools::StringReplaceAll(std::string& str, const std::string& search, const std::string& replace)
{
	std::string::size_type pos = 0;
    while ((pos = str.find(search, pos)) != std::string::npos)
	{
		str.replace(pos, search.length(), replace);
		pos += replace.length();
    }
}

