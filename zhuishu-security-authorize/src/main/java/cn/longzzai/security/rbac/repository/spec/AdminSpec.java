/**
 * 
 */
package cn.longzzai.security.rbac.repository.spec;

import cn.longzzai.security.rbac.dto.AdminCondition;
import cn.longzzai.security.rbac.repository.support.LongzzaiSpecification;
import cn.longzzai.security.rbac.repository.support.QueryWraper;
import cn.longzzai.security.rbac.domain.Admin;

/**
 * @author zhailiang
 *
 */
public class AdminSpec extends LongzzaiSpecification<Admin, AdminCondition> {

	public AdminSpec(AdminCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Admin> queryWraper) {
		addLikeCondition(queryWraper, "username");
	}

}
