package com.emrubik.springcloud.api.idm;

import com.emrubik.springcloud.domain.authority.PermissionInfo;
import com.emrubik.springcloud.domain.user.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "springcloud-idm")
public interface IUserService {

//  @PostMapping("/user/validate")
//  public User validate(@RequestParam("username") String username, @RequestParam("password") String password);

  @PostMapping("/user/validate")
  public User validate(User user);

  @RequestMapping(value="/api/user/{id}/permissions",method = RequestMethod.GET)
  public List<PermissionInfo> getPermissionByUserId(@PathVariable("id") String userId);

  @RequestMapping(value="/api/permissions",method = RequestMethod.GET)
  public List<PermissionInfo> getAllPermissionInfo();
}
