/**
 * Copyright (c) 2019 See AUTHORS file
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the mini2Dx nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mini2Dx.tiled;

import com.badlogic.gdx.files.FileHandle;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mini2Dx.tiled.exception.TiledException;

public class TiledObjectTemplateTest {
	private static TiledMap tiledMap;

	@BeforeClass
	public static void loadMap() throws TiledException {
		FileHandle file = new FileHandle(Thread.currentThread()
				.getContextClassLoader().getResource("orthogonal_tsx.tmx").getFile());
		tiledMap = new TiledMap(file, false, false);
	}

	@Test
	public void testTiledObjectTemplate() throws TiledException  {
		final String objectGroupName = "Objects";
		final String propertyName = "testProperty";

		Assert.assertEquals(1, tiledMap.getTilesets().size);

		final TiledObject templateObject = tiledMap.getObjectGroup(objectGroupName).getObjectById(3);
		Assert.assertEquals("SUCCESS", templateObject.getProperty(propertyName));

		final TiledObject overrideTemplateObject = tiledMap.getObjectGroup(objectGroupName).getObjectById(4);
		Assert.assertEquals("FAILED", overrideTemplateObject.getProperty(propertyName));
	}
}
