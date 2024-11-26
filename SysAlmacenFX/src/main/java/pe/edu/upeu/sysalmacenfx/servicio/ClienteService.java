package pe.edu.upeu.sysalmacenfx.servicio;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ModeloDataAutocomplet;
import pe.edu.upeu.sysalmacenfx.modelo.Cliente;
import pe.edu.upeu.sysalmacenfx.repositorio.ClienteRepository;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;


    Logger logger= LoggerFactory.getLogger(ClienteService.class);

    public Cliente save(Cliente cliente) {
        return repo.save(cliente);
    }

    public List<Cliente> list() {
        return repo.findAll();
    }

    public Cliente update(Cliente cliente, String dniruc) {
        Cliente clienteExistente = repo.findById(dniruc).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setNombres(cliente.getNombres());
            clienteExistente.setRepLegal(cliente.getRepLegal());
            clienteExistente.setTipoDocumento(cliente.getTipoDocumento());
            return repo.save(clienteExistente);
        }
        return null;
    }

    public void delete(String dniruc) {
        repo.deleteById(dniruc);
    }

    public Cliente searchById(String dniruc) {
        return repo.findById(dniruc).orElse(null);
    }

    public List<ModeloDataAutocomplet> listAutoCompletCliente() {
        List<ModeloDataAutocomplet> listarclientes = new ArrayList<>();
        try {
            for (Cliente cliente : repo.findAll()) {
                ModeloDataAutocomplet data = new ModeloDataAutocomplet();
                data.setIdx(cliente.getDniruc());
                data.setNameDysplay(cliente.getNombres());
                data.setOtherData(cliente.getTipoDocumento());
                listarclientes.add(data);
            }
        } catch (Exception e) {
            logger.error("Error durante la operación", e);
        }
        return listarclientes;
    }





    private Connection dataSource;

    public ClienteService(Connection dataSource) {
        this.dataSource = dataSource;
    }



    public File getFile(String filex) {
        File newFolder = new File("jasper");
        String ruta = newFolder.getAbsolutePath();
        //CAMINO = Paths.get(ruta+"/"+"reporte1.jrxml");
        Path CAMINO = Paths.get(ruta + "/" + filex);
        System.out.println("Llegasss Ruta 2:" + CAMINO.toAbsolutePath().toFile());
        return CAMINO.toFile();
    }
    public JasperPrint runReport(Long idv) throws JRException, SQLException {
        // Verificar si la venta existe
        if (!repo.existsById(idv)) {
            throw new IllegalArgumentException("La venta con id " + idv + " no existe");
        }
        HashMap<String, Object> param = new HashMap<>();
        // Obtener ruta de la imagen
        //String imgen = getFile("logoupeu.png").getAbsolutePath();

        // Agregar parámetros
        param.put("dniruc", idv);

       // param.put("urljasper", urljasper);
        // Cargar el diseño del informe
        JasperDesign jdesign = JRXmlLoader.load(getFile("listacli.jrxml"));
        JasperReport jreport = JasperCompileManager.compileReport(jdesign);
        // Llenar el informe
        return JasperFillManager.fillReport(jreport, param, dataSource.getConnection());
    }

}