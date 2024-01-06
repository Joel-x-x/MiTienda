package com.mitienda.domain.datosempresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosEmpresaRepository extends JpaRepository<DatosEmpresa, Long> {
}
