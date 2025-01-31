package LPBCLASES;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import LPB.MenuJornadas;

public class ExportarXML {

    public void exportarTemporada(Temporada temporada, String filePath, MenuJornadas menuJornadas) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Elemento raíz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("temporadas");
            doc.appendChild(rootElement);

            // Temporada
            Element temporadaElement = doc.createElement("temporada");
            rootElement.appendChild(temporadaElement);

            // Atributos de la temporada
            Element nombreElement = doc.createElement("nombre");
            nombreElement.appendChild(doc.createTextNode(temporada.getPeriodo()));
            temporadaElement.appendChild(nombreElement);

            Element estadoElement = doc.createElement("estado");
            estadoElement.appendChild(doc.createTextNode(temporada.getEstado()));
            temporadaElement.appendChild(estadoElement);

            // Equipos
            Element equiposElement = doc.createElement("equipos");
            temporadaElement.appendChild(equiposElement);

            for (int i = 0; i < menuJornadas.getTablaClasificacion().getRowCount(); i++) {
                Element equipoElement = doc.createElement("equipo");
                equiposElement.appendChild(equipoElement);

                Element nombreEquipoElement = doc.createElement("nombre");
                nombreEquipoElement.appendChild(doc.createTextNode((String) menuJornadas.getTablaClasificacion().getValueAt(i, 1)));
                equipoElement.appendChild(nombreEquipoElement);

                Element puntosElement = doc.createElement("puntos");
                puntosElement.appendChild(doc.createTextNode(String.valueOf(menuJornadas.getTablaClasificacion().getValueAt(i, 2))));
                equipoElement.appendChild(puntosElement);

                Element partidosJugadosElement = doc.createElement("partidosJugados");
                partidosJugadosElement.appendChild(doc.createTextNode(String.valueOf(menuJornadas.getTablaClasificacion().getValueAt(i, 3))));
                equipoElement.appendChild(partidosJugadosElement);

                Element partidosGanadosElement = doc.createElement("partidosGanados");
                partidosGanadosElement.appendChild(doc.createTextNode(String.valueOf(menuJornadas.getTablaClasificacion().getValueAt(i, 4))));
                equipoElement.appendChild(partidosGanadosElement);

                Element partidosPerdidosElement = doc.createElement("partidosPerdidos");
                partidosPerdidosElement.appendChild(doc.createTextNode(String.valueOf(menuJornadas.getTablaClasificacion().getValueAt(i, 5))));
                equipoElement.appendChild(partidosPerdidosElement);

                Element puntosFavorElement = doc.createElement("puntosFavor");
                puntosFavorElement.appendChild(doc.createTextNode(String.valueOf(menuJornadas.getTablaClasificacion().getValueAt(i, 6))));
                equipoElement.appendChild(puntosFavorElement);

                Element puntosContraElement = doc.createElement("puntosContra");
                puntosContraElement.appendChild(doc.createTextNode(String.valueOf(menuJornadas.getTablaClasificacion().getValueAt(i, 7))));
                equipoElement.appendChild(puntosContraElement);

                Element diferenciaPuntosElement = doc.createElement("diferenciaPuntos");
                diferenciaPuntosElement.appendChild(doc.createTextNode(String.valueOf(menuJornadas.getTablaClasificacion().getValueAt(i, 8))));
                equipoElement.appendChild(diferenciaPuntosElement);
            }

            // Jornadas
            Element jornadasElement = doc.createElement("jornadas");
            temporadaElement.appendChild(jornadasElement);

            for (Jornada jornada : temporada.getJornadas()) {
                Element jornadaElement = doc.createElement("jornada");
                jornadasElement.appendChild(jornadaElement);

                Element numeroElement = doc.createElement("numero");
                numeroElement.appendChild(doc.createTextNode(String.valueOf(jornada.getNumero())));
                jornadaElement.appendChild(numeroElement);

                Element partidosElement = doc.createElement("partidos");
                jornadaElement.appendChild(partidosElement);

                for (Partido partido : jornada.getPartidos()) {
                    Element partidoElement = doc.createElement("partido");
                    partidosElement.appendChild(partidoElement);

                    Element fechaElement = doc.createElement("fecha");
                    fechaElement.appendChild(doc.createTextNode(partido.getFecha()));
                    partidoElement.appendChild(fechaElement);

                    Element horaElement = doc.createElement("hora");
                    horaElement.appendChild(doc.createTextNode(partido.getHora()));
                    partidoElement.appendChild(horaElement);

                    Element equipo1Element = doc.createElement("equipo1");
                    equipo1Element.appendChild(doc.createTextNode(partido.getEquipo1()));
                    partidoElement.appendChild(equipo1Element);

                    Element equipo2Element = doc.createElement("equipo2");
                    equipo2Element.appendChild(doc.createTextNode(partido.getEquipo2()));
                    partidoElement.appendChild(equipo2Element);

                    Element resultadoElement = doc.createElement("resultado");
                    resultadoElement.appendChild(doc.createTextNode(partido.getResultado()));
                    partidoElement.appendChild(resultadoElement);
                }
            }

            // Escribir el contenido en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);

            System.out.println("Archivo XML guardado en: " + filePath);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}