package pe.edu.upeu.sysalmacenfx.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysalmacenfx.modelo.Sale;
import pe.edu.upeu.sysalmacenfx.servicio.SalesService;


import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    // Obtener todas las ventas
    @GetMapping
    public List<Sale> getAllSales() {
        return salesService.getAllSales();
    }

    // Obtener una venta por su ID
    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Integer id) {
        return salesService.getSaleById(id);
    }

    // Crear una nueva venta
    @PostMapping
    public Sale addSale(@RequestBody Sale sale) {
        return salesService.addSale(sale);
    }

    // Actualizar una venta existente
    @PutMapping("/{id}")
    public Sale updateSale(@PathVariable Integer id, @RequestBody Sale sale) {
        //sale.setId(id); // Establece el ID de la venta existente para actualizarla
        return salesService.updateSale(sale);
    }

    // Eliminar una venta por su ID
    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Integer id) {
        salesService.deleteSale(id);
    }
}


