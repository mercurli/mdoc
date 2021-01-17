package ${entityPackage};

import com.mer.mdoc.modules.system.entity.base.BaseEntity;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.Entity;
<#if dateFlag>
import java.util.Date;
</#if>
/**
 *
 * @author mer
 * @version 0.1.4
 * @date 2019/9/23
 */
@Setter
@Getter
@Entity
public class ${entityName} extends BaseEntity {

${content}}