/*
 * Copyright 2011-2012 Paddy Byers
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.meshpoint.anode.java;

import org.meshpoint.anode.idl.Types;

public class LongArray extends Array implements org.w3c.dom.LongArray {
	private long[] data;

	public LongArray(long[] data) {
		this(data, true);
	}

	public LongArray(long[] data, boolean isFixedLength) {
		super(Types.TYPE_LONG|Types.TYPE_ARRAY, isFixedLength);
		this.data = data;
	}

	@Override
	public int getLength() {
		return data.length;
	}

	@Override
	public void setLength(int length) {
		if(isFixedLength) throw new UnsupportedOperationException();
		if(length != data.length) {
			long[] newData = new long[length];
			System.arraycopy(data, 0, newData, 0, Math.min(length, data.length));
			data = newData;
		}
	}

	@Override
	public long getElement(int index) {
		return data[index];
	}

	@Override
	public void setElement(int index, long value) {
		if(!isFixedLength && index >= data.length) {
			setLength(index + 1);
			for(int i = data.length; i < index; i++) data[i] = value;
		}
		data[index] = value;
	}

}
