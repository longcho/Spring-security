/**
 * 
 */
package cn.longzzai.security.rbac.repository;

import cn.longzzai.security.rbac.domain.Resource;
import org.springframework.stereotype.Repository;

/**
 * @author zhailiang
 *
 */
@Repository
public interface ResourceRepository extends ImoocRepository<Resource> {

	Resource findByName(String name);

}
