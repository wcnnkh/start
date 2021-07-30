package scw.integration.taxi;

import scw.event.ChangeEvent;
import scw.event.EventDispatcher;
import scw.integration.taxi.dto.Member;

/**
 * 位置变化事件注册
 * 
 * @author shuchaowen
 *
 */
public interface TaxiLocationEventDispatcher extends EventDispatcher<ChangeEvent<Member>> {
}
