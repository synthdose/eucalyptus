/*************************************************************************
 * Copyright 2009-2015 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you need
 * additional information or have any questions.
 *
 * This file may incorporate work covered under the following copyright
 * and permission notice:
 *
 *   Software License Agreement (BSD License)
 *
 *   Copyright (c) 2008, Regents of the University of California
 *   All rights reserved.
 *
 *   Redistribution and use of this software in source and binary forms,
 *   with or without modification, are permitted provided that the
 *   following conditions are met:
 *
 *     Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *     Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer
 *     in the documentation and/or other materials provided with the
 *     distribution.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *   "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *   LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *   FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *   COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *   BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *   CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 *   ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *   POSSIBILITY OF SUCH DAMAGE. USERS OF THIS SOFTWARE ACKNOWLEDGE
 *   THE POSSIBLE PRESENCE OF OTHER OPEN SOURCE LICENSED MATERIAL,
 *   COPYRIGHTED MATERIAL OR PATENTED MATERIAL IN THIS SOFTWARE,
 *   AND IF ANY SUCH MATERIAL IS DISCOVERED THE PARTY DISCOVERING
 *   IT MAY INFORM DR. RICH WOLSKI AT THE UNIVERSITY OF CALIFORNIA,
 *   SANTA BARBARA WHO WILL THEN ASCERTAIN THE MOST APPROPRIATE REMEDY,
 *   WHICH IN THE REGENTS' DISCRETION MAY INCLUDE, WITHOUT LIMITATION,
 *   REPLACEMENT OF THE CODE SO IDENTIFIED, LICENSING OF THE CODE SO
 *   IDENTIFIED, OR WITHDRAWAL OF THE CODE CAPABILITY TO THE EXTENT
 *   NEEDED TO COMPLY WITH ANY SUCH LICENSES OR RIGHTS.
 ************************************************************************/

package com.eucalyptus.auth.principal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.eucalyptus.auth.AuthException;
import com.eucalyptus.auth.PolicyParseException;
import com.eucalyptus.auth.ServerCertificate;
import com.eucalyptus.auth.policy.PolicyResourceType;
import com.eucalyptus.auth.policy.PolicySpec;
import com.eucalyptus.component.annotation.PolicyVendor;
import com.eucalyptus.util.RestrictedType;

/**
 * The interface for the user account.
 *
 *         FIXME:YE: We are missing the standard 12-digit amazon (not AWS) account identifier. We
 *         must be
 *         able to support the amazon 12-digit account IDs for existing users and future service
 *         integrations. I put some notes in Accounts and a test implementation using @PrePersist to
 *         generate the value in {@link AccountEntity#generateOnCommit()} and marked it as
 *         @Column(unique=true}.
 * 
 * @see {@link com.eucalyptus.auth.Accounts}
 * @see {@link com.eucalyptus.auth.entities.AccountEntity#generateOnCommit()}
 */
@PolicyVendor( PolicySpec.VENDOR_IAM )
@PolicyResourceType( PolicySpec.IAM_RESOURCE_ACCOUNT )
public interface Account extends AccountIdentifiers, BasePrincipal, RestrictedType, Serializable {

  public void setName( String name ) throws AuthException;

  /**
   * Set name without performing syntax validation
   */
  public void setNameUnsafe( String name ) throws AuthException;

  public List<EuareUser> getUsers( ) throws AuthException;
  
  public List<Group> getGroups( ) throws AuthException;

  public List<EuareRole> getRoles( ) throws AuthException;

  public List<? extends BaseInstanceProfile> getInstanceProfiles( ) throws AuthException;

  public EuareUser addUser( String userName, String path, boolean enabled, Map<String, String> info ) throws AuthException;
  public void deleteUser( String userName, boolean forceDeleteAdmin, boolean recursive ) throws AuthException;

  public EuareRole addRole( String roleName, String path, String assumeRolePolicy ) throws AuthException, PolicyParseException;
  public void deleteRole( String roleName ) throws AuthException;

  public Group addGroup( String groupName, String path ) throws AuthException;
  public void deleteGroup( String groupName, boolean recursive ) throws AuthException;

  public <IPT extends BaseInstanceProfile> IPT addInstanceProfile( String instanceProfileName, String path ) throws AuthException;
  public void deleteInstanceProfile( String instanceProfileName ) throws AuthException;
  
  public ServerCertificate addServerCertificate(String certName, String certBody, String certChain, String path, String pk) throws AuthException;
  public ServerCertificate deleteServerCertificate(String certName) throws AuthException;
  
  public Group lookupGroupByName( String groupName ) throws AuthException;
  
  public EuareUser lookupUserByName( String userName ) throws AuthException;

  public EuareRole lookupRoleByName( String roleName ) throws AuthException;

  public <IPT extends BaseInstanceProfile> IPT lookupInstanceProfileByName( String instanceProfileName ) throws AuthException;

  public EuareUser lookupAdmin() throws AuthException;
  
  public ServerCertificate lookupServerCertificate(String certName) throws AuthException;
  public List<ServerCertificate> listServerCertificates(String pathPrefix) throws AuthException;
  public void updateServerCeritificate(String certName, String newCertName, String newPath) throws AuthException;

  public String getAccountNumber( );
  public String getCanonicalId( );

}
