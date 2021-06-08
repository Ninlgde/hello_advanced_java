include "user.thrift"
include "exception.thrift"

namespace java com.ninlgde.rpc.thrift

service  UserService {

  /**保存用户*/
  bool save(1:user.User user),

  /**根据name获取用户列表*/
  list<user.User> findUsersByName(1:string name),

  /**删除用户*/
  void deleteByUserId(1:i32 userId) throws (1: exception.UserNotFoundException e)
}