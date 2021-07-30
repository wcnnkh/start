package scw.integration.taxi;

import scw.event.EventDispatcher;
import scw.event.ObjectEvent;
import scw.integration.taxi.dto.PostOrder;

/**
 * 订单事件分发
 * 
 * @author shuchaowen
 *
 */
public interface OrderEventDispatcher extends EventDispatcher<ObjectEvent<PostOrder>> {
}
