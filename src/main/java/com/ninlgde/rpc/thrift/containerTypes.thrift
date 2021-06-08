include "user.thrift"

namespace java com.ninlgde.rpc.thrift

struct ContainerTypes {

		1: list<string> stringValueList;
		2: set<string> stringValueSet;
		3: map<string,string> stringValueMap;
		4: list<user.User> userList;

}