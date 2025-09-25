package com.smarttmt.usuabada;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.personal.PersonalEntity;
import com.smarttmt.personal.PersonalIService;
import com.smarttmt.tercero.IServiceTercero;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.DecodeAuthoritzationBasic;
import com.smarttmt.utilities.LeeCadena;
import com.smarttmt.utilities.TokenAutenticacion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UsuabadaService implements UsuabadaIService {

    private final UsuabadaIRepository usuabadaIRepository;
    private final PersonalIService personalIService;
    private final ParaoperIService iServiceGenericParaoper;
    private final IServiceTercero iServiceTercero;
    private final UsuaBadaIStoredProcedure usuaBadaIStoredProcedure;

    @Override
    public JsonNode validarAuth(String authString) throws Exception {

        log.info("init getUsuabadaPorUsuario");

        if (authString == null) {
            return null;
        }

        String[] credenciales = DecodeAuthoritzationBasic.decoderAuthorization(authString);
        String us = credenciales[0];
        String pw = credenciales[1];

        if (us == null || pw == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNodeFactory nodeFactory = objectMapper.getNodeFactory();
        ObjectNode rootNode = nodeFactory.objectNode();

        final String retorno = usuaBadaIStoredProcedure.getAutenticacion(us, pw);
        if (retorno == null) {
            rootNode.put(Constantes.ITEM_ERROR, Constantes.MENSAJE_ERROR_NO_EXISTE_USUABADA);
            return objectMapper.convertValue(rootNode, JsonNode.class);
        }

        if (retorno.contains(Constantes.ITEM_ERROR)) {
            rootNode.put(Constantes.ITEM_ERROR, LeeCadena.funLeerItems(retorno, Constantes.ITEM_ERROR));
            return objectMapper.convertValue(rootNode, JsonNode.class);
        }

        //se obtiene el personal
        PersonalEntity personal = personalIService.getPersonalPorUsuario(us);
        if (personal == null) {
            rootNode.put(Constantes.ITEM_ERROR, Constantes.MENSAJE_ERROR_NO_EXISTE_PERSONAL);
            return objectMapper.convertValue(rootNode, JsonNode.class);
        }

        //Se obtiene el tercero
        String items = iServiceTercero.getItemsTercero(personal.getPersterc());
        if (items == null) {
            rootNode.put(Constantes.ITEM_ERROR, Constantes.MENSAJE_ERROR_NO_EXISTE_TERCERO);
            return objectMapper.convertValue(rootNode, JsonNode.class);
        }

        String terctipo = LeeCadena.funLeerItems(items, Constantes.TERCTIPO);
        String tercdesc = LeeCadena.funLeerItems(items, Constantes.TERCDESC);

        String token = TokenAutenticacion.generaTokenJWT(us, personal.getPersterc(), personal.getPersnomb() + " " + personal.getPersapel(), iServiceGenericParaoper.getIdTokeWs());

        rootNode.put("token", token);
        rootNode.put("cliente", personal.getPersterc());
        rootNode.put("tipoCliente", terctipo);
        rootNode.put("usuario", personal.getPersusua());
        rootNode.put("perfil", Constantes.USUARIO_DE_GESTION);
        rootNode.put("descripcionCliente", tercdesc);
        rootNode.put("personal", personal.getPerscodi());
        rootNode.put("descUsuario", personal.getPersnomb().concat(" ").concat(personal.getPersapel()));
        rootNode.put("personalIniciales", personal.getPersnomb().substring(0, 1) + personal.getPersapel().substring(0, 1));

        log.info("exit getUsuabadaPorUsuario");

        return objectMapper.convertValue(rootNode, JsonNode.class);

    }


}