package scw.integration.taxi;

import scw.event.EventDispatcher;
import scw.event.ObjectEvent;
import scw.integration.taxi.dto.PostOrder;

public interface PassengerLocationEventDispatcher extends EventDispatcher<ObjectEvent<PostOrder>> {
}
