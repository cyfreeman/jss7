/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
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

package org.mobicents.protocols.ss7.map.service.sms;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPMessageType;
import org.mobicents.protocols.ss7.map.api.MAPOperationCode;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.mobicents.protocols.ss7.map.api.primitives.IMSI;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.service.sms.LocationInfoWithLMSI;
import org.mobicents.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMResponse;
import org.mobicents.protocols.ss7.map.primitives.IMSIImpl;
import org.mobicents.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

public class SendRoutingInfoForSMResponseImpl extends SmsMessageImpl implements SendRoutingInfoForSMResponse {
	
	protected static final int _TAG_LocationInfoWithLMSI = 0; 
	protected static final int _TAG_ExtensionContainer = 4; 

	private IMSI imsi;
	private LocationInfoWithLMSI locationInfoWithLMSI;
	private MAPExtensionContainer extensionContainer;
	
	
	public SendRoutingInfoForSMResponseImpl() {
	}	
	
	public SendRoutingInfoForSMResponseImpl(IMSI imsi, LocationInfoWithLMSI locationInfoWithLMSI, MAPExtensionContainer extensionContainer) {
		this.imsi = imsi;
		this.locationInfoWithLMSI = locationInfoWithLMSI;
		this.extensionContainer = extensionContainer;
	}	

	@Override
	public MAPMessageType getMessageType() {
		return MAPMessageType.sendRoutingInfoForSM_Response;
	}

	@Override
	public int getOperationCode() {
		return MAPOperationCode.sendRoutingInfoForSM;
	}

	@Override
	public IMSI getIMSI() {
		return this.imsi;
	}

	@Override
	public LocationInfoWithLMSI getLocationInfoWithLMSI() {
		return this.locationInfoWithLMSI;
	}

	@Override
	public MAPExtensionContainer getExtensionContainer() {
		return this.extensionContainer;
	}

	
	@Override
	public int getTag() throws MAPException {
		return Tag.SEQUENCE;
	}

	@Override
	public int getTagClass() {
		return Tag.CLASS_UNIVERSAL;
	}

	@Override
	public boolean getIsPrimitive() {
		return false;
	}

	
	@Override
	public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {

		try {
			int length = ansIS.readLength();
			this._decode(ansIS, length);
		} catch (IOException e) {
			throw new MAPParsingComponentException("IOException when decoding sendRoutingInfoForSMResponse: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		} catch (AsnException e) {
			throw new MAPParsingComponentException("AsnException when decoding sendRoutingInfoForSMResponse: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		}
	}

	@Override
	public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {

		try {
			this._decode(ansIS, length);
		} catch (IOException e) {
			throw new MAPParsingComponentException("IOException when decoding sendRoutingInfoForSMResponse: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		} catch (AsnException e) {
			throw new MAPParsingComponentException("AsnException when decoding sendRoutingInfoForSMResponse: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		}
	}

	private void _decode(AsnInputStream ansIS, int length) throws MAPParsingComponentException, IOException, AsnException {

		this.imsi = null;
		this.locationInfoWithLMSI = null;
		this.extensionContainer = null;
		
		AsnInputStream ais = ansIS.readSequenceStreamData(length);
		int num = 0;
		while (true) {
			if (ais.available() == 0)
				break;

			int tag = ais.readTag();
			switch (num) {
			case 0:
				// imsi
				if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
					throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForSMResponse.imsi: Parameter 0 bad tag or tag class or not primitive",
							MAPParsingComponentExceptionReason.MistypedParameter);
				this.imsi = new IMSIImpl();
				((IMSIImpl)this.imsi).decodeAll(ais);
				break;
				
			case 1:
				// locationInfoWithLMSI
				if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || ais.isTagPrimitive() || tag != _TAG_LocationInfoWithLMSI)
					throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForSMResponse.locationInfoWithLMSI: Parameter 1 bad tag class or tag or primitive",
							MAPParsingComponentExceptionReason.MistypedParameter);
				this.locationInfoWithLMSI = new LocationInfoWithLMSIImpl();
				((LocationInfoWithLMSIImpl)this.locationInfoWithLMSI).decodeAll(ais);
				break;

			default:
				if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
					switch (tag) {
					case _TAG_ExtensionContainer:
						if (ais.isTagPrimitive())
								throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForSMResponse.extensionContainer: Parameter extensionContainer is primitive",
										MAPParsingComponentExceptionReason.MistypedParameter);
						this.extensionContainer = new MAPExtensionContainerImpl();
						((MAPExtensionContainerImpl)this.extensionContainer).decodeAll(ais);
					break;

					default:
						ais.advanceElement();
						break;
					}

				} else {

					ais.advanceElement();
				}
				break;
			}
			
			num++;
		}

		if (num < 2)
			throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForSMResponse: Needs at least 2 mandatory parameters, found " + num,
					MAPParsingComponentExceptionReason.MistypedParameter);
	}

	@Override
	public void encodeAll(AsnOutputStream asnOs) throws MAPException {

		this.encodeAll(asnOs, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
	}

	@Override
	public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws MAPException {
		
		try {
			asnOs.writeTag(tagClass, false, tag);
			int pos = asnOs.StartContentDefiniteLength();
			this.encodeData(asnOs);
			asnOs.FinalizeContent(pos);
		} catch (AsnException e) {
			throw new MAPException("AsnException when encoding sendRoutingInfoForSMResponse: " + e.getMessage(), e);
		}
	}

	@Override
	public void encodeData(AsnOutputStream asnOs) throws MAPException {
		
		if (this.imsi == null || this.locationInfoWithLMSI == null)
			throw new MAPException("imsi and locationInfoWithLMSI must not be null");

		((IMSIImpl)this.imsi).encodeAll(asnOs);
		((LocationInfoWithLMSIImpl)this.locationInfoWithLMSI).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_LocationInfoWithLMSI);
		if (this.extensionContainer != null)
			((MAPExtensionContainerImpl)this.extensionContainer).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_ExtensionContainer);
	}	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SendRoutingInfoForSMResponse [");
		
		if(this.getMAPDialog() != null){
			sb.append("DialogId=").append(this.getMAPDialog().getDialogId());
		}

		if (this.imsi != null) {
			sb.append(", imsi=");
			sb.append(this.imsi.toString());
		}
		if (this.locationInfoWithLMSI != null) {
			sb.append(", locationInfoWithLMSI=");
			sb.append(this.locationInfoWithLMSI.toString());
		}
		if (this.extensionContainer != null) {
			sb.append(", extensionContainer=");
			sb.append(this.extensionContainer.toString());
		}

		sb.append("]");

		return sb.toString();
	}
}