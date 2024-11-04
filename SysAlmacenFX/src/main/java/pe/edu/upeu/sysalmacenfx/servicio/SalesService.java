package pe.edu.upeu.sysalmacenfx.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Sale;
import pe.edu.upeu.sysalmacenfx.repositorio.SalesRepository;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public List<Sale> getAllSales() {
        return salesRepository.findAll();
    }

    public Sale getSaleById(Integer id) {
        return salesRepository.findById(id).orElse(null);
    }

    public Sale addSale(Sale sale) {
        return salesRepository.save(sale);
    }

    public Sale updateSale(Sale sale) {
        return salesRepository.save(sale);
    }

    public void deleteSale(Integer id) {
        salesRepository.deleteById(id);
    }
}
