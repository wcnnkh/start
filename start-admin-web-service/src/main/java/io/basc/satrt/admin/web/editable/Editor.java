package io.basc.satrt.admin.web.editable;

import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.security.authority.http.HttpAuthority;
import io.basc.satrt.admin.web.editable.form.Input;

import java.util.List;

public interface Editor extends HttpAuthority {

	List<Input> getInputs(Object query);

	Object doAction(HttpChannel httpChannel);
}
