package io.basc.start.tencent.wx.api;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiDomainIp implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> ipList;
}
