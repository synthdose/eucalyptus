/*************************************************************************
 * (c) Copyright 2017 Hewlett Packard Enterprise Development Company LP
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
 ************************************************************************/
package com.eucalyptus.portal;

import com.eucalyptus.ws.protocol.QueryBindingInfo;

@QueryBindingInfo( statusCode = 400 )
public class Ec2ReportsInvalidParameterException extends Ec2ReportsServiceException {
  private static final long serialVersionUID = 1L;
  public Ec2ReportsInvalidParameterException(){
    super("InvalidParameterValue", "The supplied parameters are not valid");
  }
  public Ec2ReportsInvalidParameterException(final String msg){
    super("InvalidParameterValue", msg);
  }
}
