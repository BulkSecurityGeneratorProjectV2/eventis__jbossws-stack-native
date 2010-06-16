/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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

/*
 * JBossWS WS-Tools Generated Source
 *
 * Generation Date: Sun May 06 14:14:38 CEST 2007
 *
 * This generated source code represents a derivative work of the input to
 * the generator that produced it. Consult the input for the copyright and
 * terms of use that apply to this source code.
 */

package org.jboss.test.ws.jbws1627;


public class  Person
{

protected java.lang.String surname;

protected org.jboss.test.ws.jbws1627.NickName[]  nickNames;
public Person(){}

public Person(java.lang.String surname, org.jboss.test.ws.jbws1627.NickName[] nickNames){
this.surname=surname;
this.nickNames=nickNames;
}
public java.lang.String getSurname() { return surname ;}

public void setSurname(java.lang.String surname){ this.surname=surname; }

public org.jboss.test.ws.jbws1627.NickName[]  getNickNames() { return nickNames ;}

public void setNickNames(org.jboss.test.ws.jbws1627.NickName[] nickNames){ this.nickNames=nickNames; }

}