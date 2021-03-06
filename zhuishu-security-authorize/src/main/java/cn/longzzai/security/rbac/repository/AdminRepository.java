/**
 * 
 */
package cn.longzzai.security.rbac.repository;

import org.springframework.stereotype.Repository;

import cn.longzzai.security.rbac.domain.Admin;

/**
 * @author zhailiang
 *
 */
@Repository
public interface AdminRepository extends LongzzaiRepository<Admin> {

	Admin findByUsername(String username);

}
