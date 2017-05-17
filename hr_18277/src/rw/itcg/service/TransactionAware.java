/**
 * 
 */
package rw.itcg.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author NIYOMWUNGERI
 * @Date May 17, 2017
 */
@Transactional(propagation=Propagation.REQUIRED)
public class TransactionAware {
	
}
