/**
 * Copyright (c) 2015 See AUTHORS file
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

import com.badlogic.gdx.math.MathUtils;
import org.mini2Dx.core.exception.MdxException;
import org.mini2Dx.core.geom.Circle;
import org.mini2Dx.core.geom.Point;
import org.mini2Dx.core.geom.Polygon;
import org.mini2Dx.core.geom.Rectangle;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an object loaded from a {@link TiledMap}
 */
public class TiledObject {

	private final int id;
	private final float x, y, width, height;

	private TiledObjectShape objectShape = TiledObjectShape.RECTANGLE;
	private String name;
	private String type;
	private String polyline;
	private boolean visible;
	private int gid;
	private boolean gidFlipHorizontally, gidFlipVertically, gidFlipDiagonally;
	private Map<String, String> properties;

	private float [] points;
	private String text;
	private boolean wrapText;
	
	public TiledObject(int id, float x, float y, float width, float height) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns this object's properties
	 * @return Null if there are no properties
	 */
	public Map<String, String> getProperties() {
		return properties;
	}
	
	/**
	 * Returns if the object contains the specified property
	 * @param propertyName The property name to search for
	 * @return True if the object contains the property
	 */
	public boolean containsProperty(String propertyName) {
		if(properties == null)
			return false;
		return properties.containsKey(propertyName);
	}
	
	/**
	 * Returns the value of a specified property
	 * @param propertyName The property name to search for
	 * @return Null if there is no such property
	 */
	public String getProperty(String propertyName) {
		if(properties == null)
			return null;
		return properties.get(propertyName);
	}
	
	/**
	 * Sets the value of a specified property
	 * @param propertyName The property name to set the value for
	 * @param value The value of the property to set
	 */
	public void setProperty(String propertyName, String value) {
		if(properties == null)
			properties = new HashMap<String, String>();
		properties.put(propertyName, value);
	}

	/**
	 * Returns the ID of this object
	 * @return -1 if using a version of Tiled where IDs are not generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the name of this object
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of this object
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the type of this object
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type of this object
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Returns the x coordinate of this object in pixels
	 * @return
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Returns the y coordinate of this object in pixels
	 * @return
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Returns the width of this object in pixels
	 * @return
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Returns the height of this object in pixels
	 * @return
	 */
	public float getHeight() {
		return height;
	}
	
	/**
	 * Returns the Tile GID of this object
	 * @return
	 */
	public int getGid() {
		return gid;
	}
	
	/**
	 * Sets the Tile GID of this object
	 * @param gid
	 */
	public void setGid(int gid) {
		this.gid = gid;
	}

	public boolean isGidFlipHorizontally() {
		return gidFlipHorizontally;
	}

	public void setGidFlipHorizontally(boolean gidFlipHorizontally) {
		this.gidFlipHorizontally = gidFlipHorizontally;
	}

	public boolean isGidFlipVertically() {
		return gidFlipVertically;
	}

	public void setGidFlipVertically(boolean gidFlipVertically) {
		this.gidFlipVertically = gidFlipVertically;
	}

	public boolean isGidFlipDiagonally() {
		return gidFlipDiagonally;
	}

	public void setGidFlipDiagonally(boolean gidFlipDiagonally) {
		this.gidFlipDiagonally = gidFlipDiagonally;
	}

	/**
	 * Returns the {@link TiledObjectShape} of this object
	 * @return Defaults to {@link TiledObjectShape#RECTANGLE}
	 */
	public TiledObjectShape getObjectShape() {
		return objectShape;
	}

	/**
	 * Marks this object as a {@link TiledObjectShape#ELLIPSE}
	 */
	public void setAsEllipse() {
		objectShape = TiledObjectShape.ELLIPSE;
	}

	/**
	 * Marks this object as a {@link TiledObjectShape#POINT}
	 */
	public void setAsPoint() {
		objectShape = TiledObjectShape.POINT;
	}

	/**
	 * Marks this object as a {@link TiledObjectShape#POLYGON}
	 * @param points The points in the format <code>x1,y1 x2,y2</code>
	 */
	public void setAsPolygon(String points) {
		objectShape = TiledObjectShape.POLYGON;
		final String [] pointEntries = points.split(" ");

		this.points = new float[pointEntries.length * 2];
		for(int i = 0; i < pointEntries.length; i++) {
			final String [] xy = pointEntries[i].split(",");

			this.points[(i * 2)] = x + Float.parseFloat(xy[0]);
			this.points[(i * 2) + 1] = y + Float.parseFloat(xy[1]);
		}
	}

	/**
	 * Marks this object as a {@link TiledObjectShape#TEXT}
	 * @param text The text to display
	 * @param wrapText True if the text wraps
	 */
	public void setAsText(String text, boolean wrapText) {
		objectShape = TiledObjectShape.TEXT;
		this.text = text;
		this.wrapText = wrapText;
	}

	public String getPolyline() {
		return polyline;
	}

	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Creates a new {@link Point} from this object.
	 * Warning: If this object's shape is not {@link TiledObjectShape#POINT}, an exception will be thrown.
	 * @return A new {@link Point}
	 */
	public Point toPoint() {
		if(!objectShape.equals(TiledObjectShape.POINT)) {
			throw new MdxException("TiledObject " + id + " is not a point");
		}
		return new Point(x, y);
	}

	/**
	 * Creates a new {@link Circle} from this object.
	 * Warning: If this object's shape is not {@link TiledObjectShape#ELLIPSE}, an exception will be thrown.
	 * @return A new {@link Circle}
	 */
	public Circle toCircle() {
		if(!objectShape.equals(TiledObjectShape.ELLIPSE)) {
			throw new MdxException("TiledObject " + id + " is not a circle");
		}
		if(!MathUtils.isEqual(width, height)) {
			throw new MdxException("TiledObject " + id + " is an ellipse, not a circle");
		}
		return new Circle(x + (width * 0.5f), y + (height * 0.5f), width * 0.5f);
	}

	/**
	 * Creates a new {@link Rectangle} from this object.
	 * Warning: If this object's shape is not {@link TiledObjectShape#RECTANGLE}, an exception will be thrown.
	 * @return A new {@link Rectangle}
	 */
	public Rectangle toRectangle() {
		if(!objectShape.equals(TiledObjectShape.RECTANGLE)) {
			throw new MdxException("TiledObject " + id + " is not a rectangle");
		}
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Creates a new {@link Polygon} from this object.
	 * Warning: If this object's shape is not {@link TiledObjectShape#POLYGON}, an exception will be thrown.
	 * @return A new {@link Polygon}
	 */
	public Polygon toPolygon() {
		if(!objectShape.equals(TiledObjectShape.POLYGON)) {
			throw new MdxException("TiledObject " + id + " is not a polygon");
		}
		return new Polygon(points);
	}

	public String getText() {
		return text;
	}

	public boolean isWrapText() {
		return wrapText;
	}
}
