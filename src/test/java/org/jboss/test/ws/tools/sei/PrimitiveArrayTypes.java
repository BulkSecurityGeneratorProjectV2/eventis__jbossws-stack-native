/*
  * JBoss, Home of Professional Open Source
  * Copyright 2005, JBoss Inc., and individual contributors as indicated
  * by the @authors tag. See the copyright.txt in the distribution for a
  * full listing of individual contributors.
  *
  * This is free software; you can redistribute it and/or modify it
  * under the terms of the GNU Lesser General Public License as
  * published by the Free Software Foundation; either version 2.1 of
  * the License, or (at your option) any later version.
  *
  * This software is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this software; if not, write to the Free
  * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
  * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
  */
package org.jboss.test.ws.tools.sei;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * An SEI for primitive types
 *
 * See jaxrpc-1.1 (5.3.1)
 *
 * @author Thomas.Diesler@jboss.org
 * @since 14-Oct-2004
 */
public interface PrimitiveArrayTypes extends Remote
{
   public int[] echoInt(int[] v) throws RemoteException;

   public long[] echoLong(long[] v) throws RemoteException;

   public short[] echoShort(short[] v) throws RemoteException;

   public float[] echoFloat(float[] v) throws RemoteException;

   public double[] echoDouble(double[] v) throws RemoteException;

   public boolean[] echoBoolean(boolean[] v) throws RemoteException;

   public byte[] echoByte(byte[] v) throws RemoteException;
}