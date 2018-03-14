package com.emrubik.springcloud.api.idm;

import com.emrubik.springcloud.domain.authority.PermissionInfo;
import com.emrubik.springcloud.domain.user.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "springcloud-idm")
public interface IUserService {

  @RequestMapping(value = "/api/user/validate", method = RequestMethod.POST)
  public UserInfo validate(@RequestParam("username") String username, @RequestParam("password") String password);

  @RequestMapping(value="/api/user/{id}/permissions",method = RequestMethod.GET)
  public List<PermissionInfo> getPermissionByUserId(@PathVariable("id") String userId);

  @RequestMapping(value="/api/permissions",method = RequestMethod.GET)
  public List<PermissionInfo> getAllPermissionInfo();
}
