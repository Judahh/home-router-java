/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import model.IdentifierModel;

/**
 * 
 * @author JH
 */
public class CommandHandler {
	private Prompt prompt;
	private IdentifierModel identifier;

	public CommandHandler(Prompt prompt) {
		this.prompt = prompt;
		this.identifier = new IdentifierModel(1, "0/1/0");
		identifier.setSubPort(".2");
	}

	public CommandHandler(int level) {
		this.prompt = Prompt.values()[level];
	}

	public IdentifierModel getIdentifier() {
		return identifier;
	}

	public void setIdentifier(IdentifierModel identifier) {
		this.identifier = identifier;
	}

	public void setLevel(int level) {
		this.prompt = Prompt.values()[level];
	}

	public void setLevel(Prompt prompt) {
		this.prompt = prompt;
	}

	public int getLevel() {
		int index;
		for (index = 0; index < Prompt.values().length && Prompt.values()[index] != this.prompt; index++) {
		}
		return index;
	}

	public Prompt getLevelPrompt() {
		return this.prompt;
	}

	public Prompt[] getPromptValues() {
		return Prompt.values();
	}

	public enum Prompt {
		Logged_Off, A, B, configB, appnB, ca_identityB, cfg_lan_Ether_10B, cfg_adap_Ether_10_1B, config_cert_chainB, config_controllerB, config_ctrl_casB, config_crypto_mapB, config_crypto_transB, config_dialpeerB, config_ext_naclB, config_std_naclB, config_hubB, config_ifB, config_if_atm_vcB, config_vc_classB, config_subifB, config_ipx_routerB, config_isakmpB, config_keychainB, config_keychain_keyB, config_lineB, config_map_classB, config_map_listB, config_modem_poolB, config_poll_grB, config_pubkeyB, config_pubkey_keyB, config_route_mapB, config_routerB, config_rtrB, config_voiceportB, lane_config_databB, mpoa_client_configB, mpoa_server_configB, tn3270_serverB, tn3270_puB, tn3270_dlurB, tn3270_dlur_sapB, tn3270_dlur_puB, configBAsk, Logged_OffAsk, Logged_OffConsoleAsk
	}

	public String getPrompt() {
		return getPrompt(this.prompt);
	}

	public String getPrompt(Prompt prompt) {
		switch (prompt) {
		case Logged_Off:
			return "Console port";
		case A:
			return ">";
		case B:
			return "#";
		case configB:
			return "(config)#";
		case appnB:
			return "(appn)#";
		case ca_identityB:
			return "(ca-identity)#";
		case cfg_lan_Ether_10B:
			return "(cfg-lan-Ether 10)#";
		case cfg_adap_Ether_10_1B:
			return "(cfg-adap-Ether 10-1)#";
		case config_cert_chainB:
			return "(config-cert-chain)#";
		case config_controllerB:
			return "(config-controller)#";
		case config_ctrl_casB:
			return "(config-ctrl-cas)#";
		case config_crypto_mapB:
			return "(config-crypto-map)#";
		case config_crypto_transB:
			return "(config-crypto-trans)#";
		case config_dialpeerB:
			return "config_dialpeerB";
		case config_ext_naclB:
			return "(config-ext-nacl)#";
		case config_std_naclB:
			return "(config-std-nacl)#";
		case config_hubB:
			return "(config-hub)#";
		case config_ifB:
			return "(config-if)#";
		case config_if_atm_vcB:
			return "(config-if-atm-vc)#";
		case config_vc_classB:
			return "(config-vc-class)#";
		case config_subifB:
			return "(config-subif)#";
		case config_ipx_routerB:
			return "(config-ipx-router)#";
		case config_isakmpB:
			return "(config-isakmp)#";
		case config_keychainB:
			return "(config-keychain)#";
		case config_keychain_keyB:
			return "(config-keychain-key)#";
		case config_lineB:
			return "(config-line)#";
		case config_map_classB:
			return "(config-map-class)#";
		case config_map_listB:
			return "(config-map-list)#";
		case config_modem_poolB:
			return "(config-modem-pool)#";
		case config_poll_grB:
			return "(config-poll-gr)#";
		case config_pubkeyB:
			return "(config-pubkey)#";
		case config_pubkey_keyB:
			return "(config-pubkey-key)#";
		case config_route_mapB:
			return "(config-route-map)#";
		case config_routerB:
			return "(config-router)#";
		case config_rtrB:
			return "(config-rtr)#";
		case config_voiceportB:
			return "(config-voiceport)#";
		case lane_config_databB:
			return "(lane-config-datab)#";
		case mpoa_client_configB:
			return "(mpoa-client-config)#";
		case mpoa_server_configB:
			return "(mpoa-server-config)#";
		case tn3270_serverB:
			return "(tn3270-server)#";
		case tn3270_puB:
			return "(tn3270-pu)#";
		case tn3270_dlurB:
			return "(tn3270-dlur)#";
		case tn3270_dlur_sapB:
			return "(tn3270-dlur-sap)#";
		case tn3270_dlur_puB:
			return "(tn3270-dlur-pu)#";
		case configBAsk:
			return "Configuring from terminal, memory, or network [terminal]?";
		case Logged_OffAsk:
			return "Press ENTER to get the prompt.";
		case Logged_OffConsoleAsk:
			return "Press RETURN to get started.";
		default:
			return "######";
		}
	}

	public String getCMD(Prompt prompt) {// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
		switch (prompt) {// Todos os comandos estao no Excel (alguns comandos
							// aqui presentes podem ser variaveis tem que
							// implementar isto tb)
		case B:
			return "enable\r\n";
		case configB:
			return "config terminal\r\n";
		case appnB:
			return "appn mode\r\n";
		case ca_identityB:
			return "crypto ca identity\r\n";
		case cfg_lan_Ether_10B:
			return " lan ethernet 10\r\n";
		case cfg_adap_Ether_10_1B:
			return "adapter 1 4.5.6\r\n";
		case config_cert_chainB:
			return "crypto ca certificate\r\n";
		case config_controllerB:
			return "controller t1 0/0\r\n";// adicionar esse tipo de interface
		case config_ctrl_casB:
			return " cas-custom 1\r\n";
		case config_crypto_mapB:
			return "crypto map Research 10\r\n";
		case config_crypto_transB:
			return "crypto ipsec transform-set\r\n";
		case config_dialpeerB:
			return " dial peer voice 1 pots\r\n";
		case config_ext_naclB:
		case config_std_naclB:
			return "ip access-list extended flag\r\n";
		case config_hubB:
			return "hub ethernet 0 1 3\r\n";
		case config_ifB:
			return "interface " + identifier.getInterface() + " " + identifier.getPort() + "\r\n";
		case config_if_atm_vcB:
			return "pvc 0/33\r\n";
		case config_vc_classB:
			return "vc-class atm pvc1\r\n";
		case config_subifB:
			return "encapsulation frame-relay\r\ninterface " + identifier.getInterface() + " " + identifier.getPort()
					+ identifier.getSubPort() + "\r\n";
		case config_ipx_routerB:
			return "ipx router rip\r\n";
		case config_isakmpB:
			return " crypto isakmp policy\r\n";
		case config_keychainB:
			return "keychain blue\r\n";
		case config_keychain_keyB:
			return "key 10\r\n";
		case config_lineB:
			return "line vty 0 4\r\n";
		case config_map_classB:
			return "map-class atm aaa\r\n";
		case config_map_listB:
			return "map-list atm\r\n";
		case config_modem_poolB:
			return "modem-pool v90service\r\n";
		case config_poll_grB:
			return "syscon poll-group cmlineinfo\r\n";
		case config_pubkeyB:
			return "addressed-key\r\n";
		case config_pubkey_keyB:
			return "crypto key pubkey-chain rsa\r\n";
		case config_route_mapB:
			return "route-map arizona\r\n";
		case config_routerB:
			return "router rip\r\n";
		case config_rtrB:
			return "rtr 1\r\n";
		case config_voiceportB:
			return "voice port 1/1/2\r\n";// adicionar esse tipo de interface
		case lane_config_databB:
			return "lane database red\r\n";
		case mpoa_client_configB:
			return "mpoa client config name ip_mpc\r\n";
		case mpoa_server_configB:
			return "mpoa server config name ip_mps\r\n";
		case tn3270_serverB:
			return "tn3270-server\r\n";
		case tn3270_puB:
			return "pu PU1 05d00001 10.0.0.1 token-adapter 1 8 rmac 4000.0000.0001 rsap 4\r\n";
		case tn3270_dlurB:
			return "dlur\r\n";
		case tn3270_dlur_sapB:
			return "lsap\r\n";
		case tn3270_dlur_puB:
			return "pu PU1 05d00001 10.0.0.1 token-adapter 1 8 rmac 4000.0000.0001 rsap 4\r\n";
		default:
			return "";
		}
	}
}