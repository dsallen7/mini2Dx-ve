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
package org.mini2Dx.core.graphics.viewport;

import org.mini2Dx.core.util.Scaling;

/**
 * A {@link Viewport} with a fixed virtual screen size. The virtual size will be scaled to fill in the game window.
 * This means that parts of the game may be rendered outside of the window/off-screen. The aspect ratio will always be maintained.
 */
public class FillViewport extends ScalingViewport {

	/**
	 * Constructor
	 * @param worldWidth The virtual screen width
	 * @param worldHeight The virtual screen height
	 */
	public FillViewport(float worldWidth, float worldHeight) {
		this(false ,worldWidth, worldHeight);
	}

	/**
	 * Constructor
	 * @param powerOfTwo True if scaling should only be applied in powers of two
	 * @param worldWidth The virtual screen width
	 * @param worldHeight The virtual screen height
	 */
	public FillViewport(boolean powerOfTwo, float worldWidth, float worldHeight) {
		super(Scaling.FILL, powerOfTwo,worldWidth, worldHeight);
	}
}
