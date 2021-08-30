package io.basc.start.enums;

import io.basc.framework.lang.NotSupportedException;
import io.basc.framework.util.CalendarUtils;
import io.basc.framework.util.XTime;

/**
 * 连续性状态
 * 
 * @author shuchaowen
 *
 */
public enum ContinuityStatus {
	/**
	 * 相同的
	 */
	SAME(0),
	/**
	 * 连续的
	 */
	CONTINUITY(1),
	/**
	 * 不连续的
	 */
	DISCONTINUOUS(-1);

	private final int status;

	ContinuityStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public static ContinuityStatus forValue(int status) {
		if (status == 0) {
			return SAME;
		} else if (status == 1) {
			return CONTINUITY;
		} else if (status == -1) {
			return DISCONTINUOUS;
		}
		return null;
	}
	
	public static ContinuityStatus getContinuityStatus(long time1, long time2) {
		return getContinuityStatus(time1, time2, ContinuityCycle.DAY);
	}

	public static ContinuityStatus getContinuityStatus(long time1, long time2, ContinuityCycle cycle) {
		if (cycle == ContinuityCycle.DAY) {
			long t = CalendarUtils.getDayBeginCalendar(time2).getTimeInMillis()
					- CalendarUtils.getDayBeginCalendar(time1).getTimeInMillis();
			if (t == 0) {// 同一天
				return SAME;
			}

			return t == XTime.ONE_DAY ? CONTINUITY : DISCONTINUOUS;
		}

		throw new NotSupportedException(cycle.name());
	}

	public static enum ContinuityCycle {
		DAY;
	}
}
