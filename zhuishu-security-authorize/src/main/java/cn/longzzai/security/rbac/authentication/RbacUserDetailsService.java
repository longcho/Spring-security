/**
 * 
 */
package cn.longzzai.security.rbac.authentication;

import cn.longzzai.security.rbac.domain.Admin;
import cn.longzzai.security.rbac.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhailiang
 *
 */
@Slf4j
@Component
@Transactional
public class RbacUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("表单登录用户名:{}" , username);
		Admin admin = adminRepository.findByUsername(username);
		admin.getUrls();
		return admin;
	}

}
