
CREATE DATABASE IF NOT EXISTS MedicalCenterNarcisaDeJesus ;
USE  MedicalCenterNarcisaDeJesus;


CREATE TABLE IF NOT EXISTS Personas (
   idPersona CHAR(10) NOT NULL PRIMARY KEY ,
   Nombre VARCHAR(200) NOT NULL,
   Tipo VARCHAR(45) NOT NULL
   );

CREATE TABLE IF NOT EXISTS Empleados (
   idEmpleado INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idEmployee CHAR(10) NOT NULL,
   Contraseña VARCHAR(45) NOT NULL,
   Cargo VARCHAR(45) NOT NULL,
  
   FOREIGN KEY (idEmployee) REFERENCES Personas (idPersona)
   );


CREATE TABLE IF NOT EXISTS Medicos (
    idMedico INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idDoctor INT NOT NULL,
    Especialidad VARCHAR(45) NOT NULL,
    HoraAtencionInicio TIME NOT NULL,
    HoraAtencionFin TIME NOT NULL,
  
    FOREIGN KEY (idDoctor) REFERENCES Empleados (idEmpleado)
    );


CREATE TABLE IF NOT EXISTS Pacientes (
    nroHistoria INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idPatient CHAR(10) NOT NULL,
    FechaNacimiento DATE NOT NULL,
    Genero  VARCHAR(45) NOT NULL,
    EstadoCivil VARCHAR(45) NOT NULL,
    GrupoSanguineo VARCHAR(45) NOT NULL,

    FOREIGN KEY (idPatient) REFERENCES Personas (idPersona)
    );



CREATE TABLE IF NOT EXISTS Operaciones (
    idOperacion INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idPerson1  INT NOT NULL,
    idPerson2 INT NOT NULL,
    FechaHoraInicio  DATETIME NOT NULL,
    Tipo  VARCHAR(45) NOT NULL,
   
    FOREIGN KEY (idPerson1) REFERENCES Medicos (idMedico),
    FOREIGN KEY (idPerson2) REFERENCES Pacientes (nroHistoria)
    );



CREATE TABLE IF NOT EXISTS PersonasCorreos (
    idPersonaCorreo INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idPersonEmail CHAR(10) NOT NULL,
    Correo VARCHAR(45) NOT NULL,

    FOREIGN KEY (idPersonEmail) REFERENCES Personas (idPersona)
    );



CREATE TABLE IF NOT EXISTS PersonasTelefonos (
    idPersonaTelefono INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idPersonPhone CHAR(10) NOT NULL,
    Telefono VARCHAR(45) NOT NULL,

    FOREIGN KEY (idPersonPhone) REFERENCES Personas (idPersona)
	);



CREATE TABLE IF NOT EXISTS PersonasDomicilios (
    idPersonaDomicilio INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idPersonHome CHAR(10) NOT NULL,
    Ciudad VARCHAR(200) NOT NULL,
    Direccion VARCHAR(200) NOT NULL,
  
    FOREIGN KEY (idPersonHome) REFERENCES Personas (idPersona)
	);



CREATE TABLE IF NOT EXISTS PacientesOcupaciones (
    idPacienteOcupacion INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idPatientWork INT NOT NULL,
    Ocupacion VARCHAR(200) NOT NULL,
  
    FOREIGN KEY (idPatientWork) REFERENCES Pacientes (nroHistoria)
    );



CREATE TABLE IF NOT EXISTS ConsultasMedicas (
    idConsulta INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idMedicalVisit INT NOT NULL,
    FechaHoraFin DATETIME NOT NULL,
    Motivos VARCHAR(656) NOT NULL,
    ExamenFisico VARCHAR(656) NOT NULL,
    Procedimiento VARCHAR(656) NOT NULL,
	esEmergencia TINYINT NOT NULL,
    Acompañante VARCHAR(200) NULL,
    Relacion VARCHAR(200) NULL,
  
    FOREIGN KEY (idMedicalVisit) REFERENCES Operaciones (idOperacion)
    );
    
    
    
CREATE TABLE IF NOT EXISTS Citas(
   idCita INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   idCite INT NOT NULL,
   Fecha DATE,
   Descripcion VARCHAR(200),
   
   FOREIGN KEY (idCite) REFERENCES ConsultasMedicas(idConsulta)
);



CREATE TABLE IF NOT EXISTS Diagnosticos (
    idDiagnostico INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idDiagnosis INT NOT NULL,
    Diagnostico VARCHAR(200) NOT NULL,
    CIE10 VARCHAR(45) NOT NULL,
    TipoAntecedente VARCHAR(45) NOT NULL,

    FOREIGN KEY (idDiagnosis) REFERENCES ConsultasMedicas (idConsulta)
    );



CREATE TABLE IF NOT EXISTS SignosVitales (
    idSignosVitales INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idVitalSigns INT NOT NULL,
    Pulso DOUBLE NOT NULL,
    FrecuenciaRespiratoria DOUBLE NOT NULL,
    PresionSistolica DOUBLE NOT NULL,
    PresionDiastolica DOUBLE NOT NULL,
    SaturacionOxigeno DOUBLE NOT NULL,
    Temperatura DOUBLE NOT NULL,
    Talla DOUBLE NOT NULL,
    Peso DOUBLE NOT NULL,
  
    FOREIGN KEY (idVitalSigns) REFERENCES ConsultasMedicas (idConsulta)
    );



CREATE TABLE IF NOT EXISTS Tratamientos (
    idTratamiento INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idTreatment INT NOT NULL,
    idSufferer  INT NOT NULL,
    FechaInicio DATE NOT NULL,
    FechaFin DATE NOT NULL,
    Medicacion VARCHAR(200) NOT NULL,
    Indicaciones VARCHAR(656) NOT NULL,

    FOREIGN KEY (idTreatment)
    REFERENCES ConsultasMedicas (idConsulta),
       FOREIGN KEY (idSufferer)
    REFERENCES Pacientes (nroHistoria)
    );


CREATE TABLE IF NOT EXISTS Ordenes (
	idOrden INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idOrder INT NOT NULL,
	fechaHoraAsistencia DATETIME NOT NULL,
	Descripcion VARCHAR(656) NOT NULL,

    FOREIGN KEY (idOrder) REFERENCES ConsultasMedicas (idConsulta)
	);


CREATE TABLE IF NOT EXISTS Examenes (
    idExamen INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Examen VARCHAR(200) NOT NULL
    );



CREATE TABLE IF NOT EXISTS Parametros (
    idParametro INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idParameter  INT NOT NULL,
    Parametro VARCHAR(200) NOT NULL,
  
    FOREIGN KEY (idParameter) REFERENCES Examenes (idExamen)
    );



CREATE TABLE IF NOT EXISTS OrdenesParametros (
    idOrdenParametro INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idPrecepto INT NOT NULL,
    idIndicador INT NOT NULL,
    
    FOREIGN KEY (idIndicador) REFERENCES Parametros(idParametro),
    FOREIGN KEY (idPrecepto) REFERENCES Ordenes(idOrden)
  
    );


CREATE TABLE IF NOT EXISTS Acciones (
	  idAccion INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	  Tipo VARCHAR(45) NOT NULL
	  );



CREATE TABLE IF NOT EXISTS MedicionesPA (
	  idMedicionPA INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	  idMeasurePA INT NOT NULL,
	  PresionSistolica DOUBLE ,
	  PresionDiastolica DOUBLE,
	  Pulso DOUBLE ,
  
      FOREIGN KEY (idMeasurePA) REFERENCES Acciones (idAccion)
      );



CREATE TABLE IF NOT EXISTS MedicionesGlucosa (
	  idMedicionGlucosa INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	  idMeasureGlucose INT NOT NULL,
	  Glucosa DOUBLE,
  
      FOREIGN KEY (idMeasureGlucose) REFERENCES Acciones (idAccion)
      );



CREATE TABLE IF NOT EXISTS AdmiMedicinas (
    idAdmiMedicina INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idAdmiMedicine INT NOT NULL,

    FOREIGN KEY (idAdmiMedicine) REFERENCES Acciones (idAccion)

    );



CREATE TABLE IF NOT EXISTS Productos (
	  idProducto INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	  NombreComercial VARCHAR(200) NOT NULL,
	  Presentacion VARCHAR(200) NOT NULL,
	  esMedicamento TINYINT NOT NULL,
	  Laboratorio VARCHAR(200) NOT NULL,
	  UbicacionPercha VARCHAR(45) NOT NULL,
	  PrecioUnitarioCosto DOUBLE ,
	  PrecioUnitarioVenta DOUBLE NOT NULL,
	  esVentaLibre TINYINT,
      CantidadDisponible INT NOT NULL
      );

               
CREATE TABLE IF NOT EXISTS TomasMedicinas (
	  idToma INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	  idTake INT NOT NULL,
	  idMedicamento INT NOT NULL,
	  Cantidad INT NOT NULL,

	  FOREIGN KEY (idTake) REFERENCES AdmiMedicinas (idAdmiMedicina),
	  FOREIGN KEY (idMedicamento) REFERENCES Productos (idProducto)
	  );



CREATE TABLE IF NOT EXISTS Horarios (
    idHorario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idSchedule INT NOT NULL,
    Hora TIME NOT NULL,
    CondicionComida VARCHAR(45) NOT NULL,

    FOREIGN KEY (idSchedule) REFERENCES Tratamientos (idTratamiento)
    );



CREATE TABLE IF NOT EXISTS HorariosAcciones (
	idHorarioAccion INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idAgenda INT NOT NULL,
    idActivity INT NOT NULL,
    Fecha DATE NOT NULL,
    HoraEjecucion TIME,
    
    FOREIGN KEY (idAgenda) REFERENCES Horarios (idHorario),
    FOREIGN KEY (idActivity) REFERENCES Acciones(idAccion)
    );

#Insertar los registros de las tablas quemadas que no necesitan consultas directas

    INSERT INTO Personas 
    VALUES ('1102371802', 'Luis Antonio Sarango Masache', 'Gerente');

    INSERT INTO PersonasTelefonos
    VALUES (1, '1102371802', '0939617120'),
           (2, '1102371802', '2258945');

    INSERT INTO PersonasDomicilios
    VALUES (1, '1102371802','Guayquil' ,'Cdla. Florida Norte Mz. 601 Villa 5'),
           (2, '1102371802', 'Guayaquil ','Cdla. Florida Norte Mz. 602 Villa 6');

    INSERT INTO PersonasCorreos
    VALUES (1, '1102371802', 'drluisarango@hotmail.es'),
           (2, '1102371802', 'drluisarango@gmail.com');

    INSERT INTO Empleados
    VALUES (1, '1102371802', 'mesigyna', 'Medico');

    INSERT INTO Medicos
    VALUES (1, 1, 'Medicina General', '06:00:00', '22:00:00');



    INSERT INTO Examenes
    VALUES (1, 'Hematologicos'),
           (2, 'Hemostaticos'),
		   (3, 'Control Lipidico'),
           (4, 'Serologicos'),
           (5, 'Bioquimicos'),
           (6, 'Enzimas'),
           (7, 'Perfil Inmunologico'),
           (8, 'Electrolito'),
           (9, 'Hormonas'),
           (10, 'Nefelometria'),
           (11, 'Marcadores Tumorales'),
           (12, 'Microbiologia y Bacteriologia'),
           (13, 'Fertilidad'),
           (14, 'IFI'),
           (15, 'Marcadores Hepaticos'),
           (16, 'Niveles Farmacos y Abuso De Drogas'),
           (17, 'Biologia Molecular'),
           (18, 'Control De Anemias'),
           (19, 'Moco Nasal'),
           (20, 'Heces'),
           (21, 'Orina');



    INSERT INTO Parametros 
    VALUES (1, 1, 'Biometria Hematica'),
           (2, 1, 'Hemoglobina'),
           (3, 1, 'Hematies'),
           (4, 1, 'Hematocritos'),
           (5, 1, 'Leucocitos'),
           (6, 1, 'Eritrosedimentacion (VSG)'),
           (7, 1, 'Plaquetas'),
           (8, 1, 'Coombs Directo'),
           (9, 1, 'Coombs Indirecto'),
           (10, 1, 'Grupo Sanguineo ABO Rh'),
           (11, 1, 'Reticulocitos'),
           (12, 1, 'Paludismo Antigeno'),
           (13, 1, 'Frotis (Sangre Periferico)'),

           (14, 2, 'T de Sangria'),
           (15, 2, 'T de Coagulacion'),
           (16, 2, 'T de Protrombina (TP)'),
           (17, 2, 'T de Tromboplastina (TPP)'),
           (18, 2, 'Fibrinogeno'),
           (19, 2, 'Retracion De Coagulo'),

           (20, 3, 'Colesterol Total'),
           (21, 3, 'HDL'),
           (22, 3, 'LDL'),
           (23, 3, 'Trigliceridos'),
           (24, 3, 'VLDL Colesterol'),

           (25, 4, 'Widal y Weill Felix'),
           (26, 4, 'Huddleson'),
           (27, 4, 'Antigeno VI'),
           (28, 4, 'Mono Test'),
           (29, 4, 'VDRL (Cualitativo)'),
           (30, 4, 'VDRL (Cuantitativo)'),
           (31, 4, 'Chaqas'),

           (32, 5, 'Urea'),
           (33, 5, 'Bun'),
           (34, 5, 'Creatinina'),
           (35, 5, 'Acido Urico'),
           (36, 5, 'Glucosa'),
           (37, 5, 'Glucosa Post Prandial'),
           (38, 5, 'Prueba Tolerancia De Glucosa'),
           (39, 5, 'Fructosamina'),
           (40, 5, 'Hemoglobina Glicosilada'),
           (41, 5, 'Lipidos Totales'),
           (42, 5, 'Fosfolipidos'),
           (43, 5, 'Hierro Total'),
           (44, 5, 'Bilirrubina Directa'),
           (45, 5, 'Bilirrubina Indirecta'),
           (46, 5, 'Proteinas Total'),
           (47, 5, 'Albuminas'),
           (48, 5, 'Globulinas'),
           (49, 5, 'Amonio'),
           (50, 5, 'HOMA IR'),
           (51, 5, 'Apo A1'),
           (52, 5, 'Apo B2'),
           (53, 5, 'Lipoproteina A'),
           (54, 5, 'Insulina'),

           (55, 6, 'Fosfatasa Alcalina'),
           (56, 6, 'Fosfatasa Acida Total'),
           (57, 6, 'Fosfatasa Acida Prostatica'),
           (58, 6, 'GOT'),
           (59, 6, 'GPT'),
           (60, 6, 'GGT'),
           (61, 6, 'LDH'),
           (62, 6, 'Amiliasa'),
           (63, 6, 'Lipasa'),
           (64, 6, 'TCPK Total'),
           (65, 6, 'CKMB'),
           (66, 6, 'Colinesterasa'),
           (67, 6, 'Aldolasa'),
           (68, 6, 'Troponina T'),
           (69, 6, 'Troponina I'),

           (70, 7, 'Herpes I (IgG)'),
           (71, 7, 'Herpes I (IgM)'),
           (72, 7, 'Herpes II (IgG)'),
           (73, 7, 'Herpes II (IgM)'),
           (74, 7, 'Toxoplasma (IgG)'),
           (75, 7, 'Toxoplasma (IgM)'),
           (76, 7, 'Citomegalovirus (IgG)'),
           (77, 7, 'Citomegalovirus (IgM)'),
           (78, 7, 'Epstein Barr (IgG)'),
           (79, 7, 'Epstein Barr (IgM)'),
           (80, 7, 'Chlamydia (IgG)'),
           (81, 7, 'Chlamydia (IgM)'),
           (82, 7, 'Serameba'),
           (83, 7, 'Anticuerpo HIV'),
           (84, 7, 'Wester Blot (HIV 1-2)'),
           (85, 7, 'Herpes Zoster'),
           (86, 7, 'Micoplasma (IgG)'),
           (87, 7, 'Pilory Test (IgG-IgA)'),
           (88, 7, 'Antic Tuberculosis (TB complex)'),
           (89, 7, 'Varicela'),
           (90, 7, 'Sarampion'),
           (91, 7, 'Antic Cisticerosis'),
           (92, 7, 'Crioglobulina'),
           (93, 7, 'Sclero 70'),
           (94, 7, 'IgE'),
           (95, 7, 'IgM'),
           (96, 7, 'IgA'),
           (97, 7, 'IgG'),
           (98, 7, 'Ac Anti Dengue IgG'),
           (99, 7, 'Ac Anti Dengue IgM'),
           (100, 7, 'Ac Anti Dengue Cualitativo'),
           (101, 7, 'Parotiditis'),

           (102, 8, 'Sodio'),
           (103, 8, 'Potasio'),
           (104, 8, 'Cloro'),
           (105, 8, 'Calcio'),
           (106, 8, 'Fosforo'),
           (107, 8, 'Magnesio'),
           (108, 8, 'Calcio Ionico'),
           (109, 8, 'Litio'),

           (110, 9, 'T4 (Trioxina Total)'),
           (111, 9, 'Ft4 (Trioxina Libre)'),
           (112, 9, 'T3 (Triyodotronina Libre)'),
           (113, 9, 'TSH'),
           (114, 9, 'TSH (Ultrasensible)'),
           (115, 9, 'Anti-Tiroglobulina (ATG)'),
           (116, 9, 'Corisol'),
           (117, 9, 'Prolactina'),
           (118, 9, 'Fsh'),
           (119, 9, 'LH'),
           (120, 9, 'Estradiol'),
           (121, 9, 'Progesterona'),
           (122, 9, 'Testosterona'),
           (123, 9, 'Testosterona Libre'),
           (124, 9, 'ACTH'),
           (125, 9, 'HCG (Cualitativa)'),
           (126, 9, 'BHCG (Cualitativa)'),
           (127, 9, 'Peptido C'),
           (128, 9, 'IGF-1'),
           (129, 9, 'DHEAS'),
           (130, 9, 'Estradiol Libre'),
           (131, 9, 'GH (Hormona De Crecimineto)'),
           (132, 9, 'GH (Hormona De Crecimiento) Con Estimulos'),
           (133, 9, 'PTH'),
           (134, 9, 'IGFBP3'),

           (135, 10, 'Inmunoglobulina G'),
           (136, 10, 'PCR Cuantitativa'),
           (137, 10, 'Ra Test Cuantitativa'),
           (138, 10, 'Asto Cuantitativa'),
           (139, 10, 'Complemento C3'),
           (140, 10, 'Complemento C4'),
           
           (141, 11, 'AFG (Alfa Feto Proteina)'),
           (142, 11, 'ACE (Antigeno Carcinoembrionario)'),
           (143, 11, 'APS (Antigeno Prostatico Especifico)'),
           (144, 11, 'APS Libre'),
           (145, 11, 'Ca 15-3'),
           (146, 11, 'Ca 125'),
           (147, 11, 'Ca 72-4'),
           (148, 11, 'Ca 19-9'),
           (149, 11, 'Ca 21-1'),
           (150, 11, 'Ca 15-3 (Mama)'),
           (151, 11, 'Ca 19-9 (Pancreas)'),
           (152, 11, 'Ca 72-4 (Estomago)'),
           (153, 11, 'Ca 125 (Ovario-Pulmon)'),

           (154, 12, 'Cultivo De Orina'),
           (155, 12, 'Cultivo De Heces'),
           (156, 12, 'Cultivo De E.X. Faringeo'),
           (157, 12, 'Cultivo De Sec vaginal'),
           (158, 12, 'Hemocultivo'),
           (159, 12, 'Cultivo De Sec Uretal'),
           (160, 12, 'Cultivo De Esputo'),
           (161, 12, 'Cultivo Para Micobacterium (TB)'),
           (162, 12, 'Cultivo Para Hongos'),
           (163, 12, 'Strep A'),
           (164, 12, 'Autovacuna'),
           (165, 12, 'Cultivo De Catheter'),
           (166, 12, 'Cultivo De Forunculo'),
           (167, 12, 'Cultivo De Liquidos'),
           (168, 12, 'Cultivo De Pustulas'),
           (169, 12, 'Cultivo De Secrecion Uretral'),
           (170, 12, 'Cultivo De Semen'),
           (171, 12, 'KOH Directo'),

           (172, 13, 'Espermatograma'),
           (173, 13, 'Cultivo y Antibiograma '),
           (174, 13, 'Test Post-Coito'),
           (175, 13, 'Score Cervical'),
           (176, 13, 'Otros'),

           (177, 14, 'Anti DNA'),
           (178, 14, 'ANA'),
           (179, 14, 'ASMA'),
           (180, 14, 'ANCA (C)(P)'),
           (181, 14, 'Anti RNP'),
           (182, 14, 'Anti Sm'),
           (183, 14, 'Anti SSA'),
           (184, 14, 'Anti SSB'),

           (185, 15, 'Anti Hav Total'),
           (186, 15, 'Anti Hav IgM-IgG'),
           (187, 15, 'Anti HBC'),
           (188, 15, 'Hbs A9'),
           (189, 15, 'HCV'),

           (190, 16, 'Acido Vaiproico'),
           (191, 16, 'Barbiturico'),
           (192, 16, 'Carbamezepina'),
           (193, 16, 'Marihuana'),
           (194, 16, 'Cocaina'),
           (195, 16, 'Oplaceos En Orina'),

           (196, 17, 'Carga Viral HIV'),
           (197, 17, 'CD4/CD8'),
           (198, 17, 'Carga Viral En Hepatitis B'),
           (199, 17, 'Carga Viral En Hepatitis C'),
           (200, 17, 'Deteccion Epstein Barr Virus'),
           (201, 17, 'Deteccion De Neisseria G'),
           (202, 17, 'Deteccion De M Tuberculosis'),

           (203, 18, 'Vitamina B12'),
           (204, 18, 'Transaferrina'),
           (205, 18, 'Hierro Serico'),
           (206, 18, 'Ac Folico'),
           (207, 18, 'Capacidad De Fijacion Fe'),
           (208, 18, 'Electroforesis De Hemoglobina'),
           (209, 18, 'Ferritina Eritropoyetina'),
           (210, 18, 'Haptoglobina'),
           (211, 18, 'Glucosa 6 Fosfato'),

           (212, 19, 'Citologia De Moco Nasal'),

           (213, 20, 'Parasitologico'),
           (214, 20, 'Clostridium Difficile'),
           (215, 20, 'Sangre Oculta'),
           (216, 20, 'Citologia De Moco Nasal'),
           (217, 20, 'Metodo De Graham (Oxiuros)'),
           (218, 20, 'Adenovirus'),
           (219, 20, 'Rotavirus'),
           (220, 20, 'Pilory En Heces'),
           (221, 20, 'Azucares Reductores'),
           (222, 20, 'Tincion De Sudan'),
           (223, 20, 'Parasitologico Por Concentracion'),

           (224, 21, 'Fisico-Quimico-Sedimento'),
           (225, 21, 'Gravindex'),
           (226, 21, 'Proteinuria Orina 24 Hrs'),
           (227, 21, 'Depuracion Creatinina Orina 24 Hrs'),
           (228, 21, 'Microalbumina En Orina Al Azar'),
           (229, 21, 'Proteina De Bence Jones'),
           (230, 21, 'Pyrilinks-D'),
           (231, 21, 'Calcio En Orina 24 Hrs'),
           (232, 21, 'Estudio De Calculo'),
           (233, 21, 'Sodio En Orina'),
           (234, 21, 'Urea En Orina 24 Hrs'),
           (235, 21, 'Potasio En Orina 24 Hrs') ; 


INSERT INTO Productos
VALUES (1, 'Glucofage XR 1000 mg', 'Tabletas',1,  'MerckSerono', 'B3', null, 0.84 , null, 30),
			   (2, 'Glucofage XR 750 mg', 'Tabletas',1,  'MerckSerono', 'B3', null, 0.79 , null, 30),
			   (3, 'Glucofage 1000 mg', 'Tabletas',1,  'MerckSerono', 'B3', null, 0.38 , null, 30),
			   (4, 'Glucofage XR 500 mg', 'Tabletas',1,  'MerckSerono', 'B3', null, 0.59 , null, 30),
			   (5, 'Amaril M 2 mg/1000 mg', 'Comprimidos Recubiertos',1,  'MerckSerono', 'B3', null, 0.57 , null, 16),
			   (6, 'Lantus 100 U/mL Insulina Inyectable', 'Solucion Inyectable',1,  'Sanofi', 'D', null, 15.92 , null, 5),
			   (7, 'Aguja Insulina DEN ', 'Aguja Esteril',1,  'Vanaguja', 'B3', null, 0.3 , null, 20),
               
			   (8, 'Ibersartan 300 mg', 'Tabletas Cubiertas ',1,  'Mk', 'B3', null, 0.41 , null, 14),
			   (9, 'Convertal 100', 'Comprimidos Recubiertos',1,  'Roemmers', 'B3', null, 0.95 , null, 10),
			   (10, 'Convertal 50', 'Comprimidos Recubiertos',1,  'Roemmers', 'A3', null, 0.69 , null, 10),
			   (11, 'Losartan 50 mg', 'Comprimidos Recubiertos',1,  'Genfar', 'C', null, 0.18 , null, 30),
			   (12, 'Micardis 80 mg', 'Comprimidos Recubiertos',1,  'Boehringer Ingelheim', 'B2', null, 1.14 , null, 28),
			   (13, 'Micardis Plus 80 mg/125 mg', 'Comprimidos',1,  'Boehringer Ingelheim', 'B2', null, 1.32 , null, 28),
               (14, 'Micardis Amlo 80 mg/5 mg', 'Comprimidos',1,  'Boehringer Ingelheim', 'B2', null, 1.5 , null, 28),
			   (15, 'Amlodipino 5 mg', 'Comprimidos',1,  'Ecuaquimica', 'B2', null, 0.19 , null, 30),
			   (16, 'Amlodipino 10 mg', 'Comprimidos',1,  'Ecuaquimica', 'B2', null, 0.21, null, 30),
			   (17, 'Minart 8 mg', 'Comprimidos Recubiertos',1,  'Merck', 'B3', null, 0.58 , null, 14),
               (18, 'Minart 10 mg', 'Comprimidos Recubiertos',1,  'Merck', 'B3', null, 0.88 , null, 14),
			   (19, 'Amlor 10 mg', 'Tabletas',1,  'Farma', 'B5', null, 0.31 , null, 30),
               (20, 'Aspirina 100 mg', 'Tabletas',1,  'Bayer', 'I2', null, 0.14 , null, 100);

#Stored Procedures


#Queries Persona
DELIMITER //
CREATE PROCEDURE createPersona(
	IN idPersona CHAR(10),
	IN nombre VARCHAR(200),
	IN tipo VARCHAR(45)
	)
BEGIN
    INSERT INTO Personas
    VALUES (idPersona, nombre, tipo);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createPersonaTelefono(
	IN idPersonPhone CHAR(10),
	IN telefono VARCHAR(45)
	)
BEGIN
    INSERT INTO PersonasTelefonos(idPersonPhone, Telefono)
    VALUES (idPersonPhone, telefono);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createPersonaCorreo(
	IN idPersonEmail CHAR(10),
	IN correo VARCHAR(45)
	)
BEGIN
    INSERT INTO PersonasCorreos(idPersonEmail, Correo)
    VALUES (idPersonEmail, correo);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createPersonaDomicilio(
	IN idPersonHome CHAR(10),
	IN ciudad VARCHAR(200),
	IN direccion VARCHAR(200)
	)
BEGIN
    INSERT INTO PersonasDomicilios(idPersonHome, Ciudad, Direccion)
    VALUES (idPersonHome, ciudad, direccion);
END
//
DELIMITER ;


#Queries Paciente
DELIMITER //
CREATE PROCEDURE createPaciente(
	IN idPatient CHAR(10),
	IN fechaNacimiento DATE,
	IN genero VARCHAR(45),
	IN estadoCivil VARCHAR(45),
	IN grupoSanguineo VARCHAR(45)
	)
BEGIN
    INSERT INTO Pacientes(idPatient, FechaNacimiento, Genero, EstadoCivil, GrupoSanguineo)
    VALUES (idPatient, fechaNacimiento, genero, estadoCivil, grupoSanguineo);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createPacienteOcupacion(
	IN idPatientWork INT,
	IN ocupacion VARCHAR(200)
	)
BEGIN
    INSERT INTO PacientesOcupaciones(idPatientWork, Ocupacion)
    VALUES (idPatientWork, ocupacion);
END
//
DELIMITER ;


#Queries Operacion
DELIMITER //
CREATE PROCEDURE createOperacion(
	IN idPerson1 INT,
	IN idPerson2 INT,
	IN fechaHoraInicio DATETIME,
	IN tipo VARCHAR(45)
	)
BEGIN
    INSERT INTO Operaciones(idPerson1, idPerson2, FechaHoraInicio, Tipo)
    VALUES (idPerson1, idPerson2, fechaHoraInicio, tipo);
END
//
DELIMITER ;

#Queries ConsultaMedica
DELIMITER //
CREATE PROCEDURE createConsulta(
	IN idMedicalVisit INT,
	IN fechaHoraFin DATETIME,
	IN motivos VARCHAR(656),
	IN examenFisico VARCHAR(656),
	IN procedimiento VARCHAR(656),
	IN isEmergency TINYINT,
	IN acompañante VARCHAR(200),
	IN relacion VARCHAR(200)
	)
BEGIN
    INSERT INTO ConsultasMedicas(idMedicalVisit, FechaHoraFin, Motivos, ExamenFisico, Procedimiento, esEmergencia, Acompañante, Relacion)
    VALUES(idMedicalVisit, fechaHoraFin, motivos, examenFisico, procedimiento, isEmergency, acompañante, relacion);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createDiagnostico(
	IN idDiagnosis INT,
	IN diagnostico VARCHAR(200),
	IN cie10 VARCHAR(45),
	IN tipoAntecedente VARCHAR(45)
	)
BEGIN
    INSERT INTO Diagnosticos(idDiagnosis, Diagnostico, CIE10, TipoAntecedente)
    VALUES(idDiagnosis, diagnostico, cie10, tipoAntecedente);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createSignosVitales(
	IN idVitalSigns INT,
	IN pulso DOUBLE,
	IN frecuenciaRespiratoria DOUBLE,
	IN presionSistolica DOUBLE,
	IN presionDiastolica DOUBLE,
	IN saturacionOxigeno DOUBLE,
	IN temperatura DOUBLE,
	IN talla DOUBLE,
	IN peso DOUBLE
	)
BEGIN
    INSERT INTO SignosVitales(idVitalSigns, Pulso, FrecuenciaRespiratoria,
    	   PresionSistolica, PresionDiastolica, SaturacionOxigeno, Temperatura, Talla, Peso)
    VALUES(idVitalSigns, pulso, frecuenciaRespiratoria, 
    	   presionSistolica, presionDiastolica, saturacionOxigeno, temperatura, talla, peso);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createTratamiento(
	IN idTreatment INT,
    IN idSufferer  INT,
	IN fechaInicio DATE,
	IN fechaFin DATE,
    IN medicacion VARCHAR(200),
    IN indicaciones VARCHAR(656)
	)
BEGIN
    INSERT INTO Tratamientos(idTreatment, idSufferer, FechaInicio, FechaFin, Medicacion, Indicaciones)
    VALUES(idTreatment, idSufferer,  fechaInicio, fechaFin, medicacion, indicaciones);
END
//
DELIMITER ;


#Queries Examenes
DELIMITER //
CREATE PROCEDURE createOrden(
	IN idOrder INT,
	IN fechaHoraAsistencia DATETIME,
	IN descripcion VARCHAR(656)
	)
BEGIN
    INSERT INTO Ordenes(idOrder, fechaHoraAsistencia, Descripcion)
    VALUES(idOrder, fechaHoraAsistencia, descripcion);
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE createOrdenParametro(
	IN idPrecepto INT,
	IN idIndicador INT
	)
BEGIN
    INSERT INTO OrdenesParametros(idPrecepto, idIndicador)
    VALUES(idPrecepto, idIndicador);
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE createAccion(
	IN tipo VARCHAR(45)
	)
BEGIN
	  INSERT INTO Acciones(Tipo)
      VALUES(tipo);
    
END
//
DELIMITER ;


#Queries Accion
DELIMITER //
CREATE PROCEDURE createMedicionPA(
	IN idMeasurePA INT,
	IN presionSistolica DOUBLE,
	IN presionDiastolica DOUBLE,
	IN pulso DOUBLE
	)
BEGIN
    INSERT INTO MedicionesPA(idMeasurePA, PresionSistolica, PresionDiastolica, Pulso)
    VALUES(idMeasurePA, presionSistolica, presionDiastolica, pulso);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createMedicionGlucosa(
	IN idMeasureGlucose INT,
	IN glucosa DOUBLE
	)
BEGIN
    INSERT INTO MedicionesGlucosa(idMeasureGlucose, Glucosa)
    VALUES(idMeasureGlucose, glucosa);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createAdmiMedicina(
	IN idAdmiMedicine INT
	)
BEGIN
    INSERT INTO AdmiMedicinas(idAdmiMedicine)
    VALUES(idAdmiMedicine);
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE createTomaMedicina(
	IN idTake INT,
	IN idMedicamento INT,
	IN cantidad INT
	)
BEGIN
    INSERT INTO TomasMedicinas(idTake, idMedicamento, Cantidad)
    VALUES (idTake, idMedicamento, cantidad);
END
//
DELIMITER ;


#Queries Horario
DELIMITER //
CREATE PROCEDURE createHorario(
	IN idSchedule INT,
	IN hora TIME,
    IN condicionComida VARCHAR(45)
	)
BEGIN
    INSERT INTO Horarios(idSchedule, Hora, CondicionComida)
    VALUES(idSchedule, hora, condicionComida);
    	   
END
//
DELIMITER ;



DELIMITER //
CREATE PROCEDURE createHorarioAccion(
	IN idAgenda INT,
	IN idActivity INT,
	IN fecha DATE,
    IN horaEjecucion TIME
	)
BEGIN
    INSERT INTO HorariosAcciones(idAgenda, idActivity, Fecha, HoraEjecucion)
    VALUES(idAgenda, idActivity, fecha, horaEjecucion);
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE createCita(
  IN idCite INT,
  IN fecha DATE,
  IN descripcion VARCHAR(200)
  )
BEGIN
    INSERT INTO Citas(idCite, Fecha, Descripcion)
    VALUES(idCite, fecha, descripcion);
END
//
DELIMITER ;

#############################################################################################################

DELIMITER //
CREATE PROCEDURE readProductoNombre(
	IN nombre VARCHAR(45)
	)
BEGIN
    SELECT IdProducto, NombreComercial, Presentacion, Laboratorio, CantidadDisponible, esVentaLibre, PrecioUnitarioVenta
    FROM Productos 
    WHERE  esMedicamento=1 AND (nombre=NombreComercial OR NombreComercial LIKE concat(nombre,"%"));
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE readProductoNombreLab(
	IN nombre VARCHAR(45),
    IN lab VARCHAR(45)
	)
BEGIN
    SELECT idProducto, NombreComercial, Presentacion, Laboratorio, CantidadDisponible, esVentaLibre, PrecioUnitarioVenta
    FROM Productos 
    WHERE esMedicamento=1 AND ((nombre=NombreComercial OR NombreComercial LIKE concat(nombre,"%")) AND (lab=Laboratorio OR Laboratorio LIKE concat(lab,"%"))  ) ;
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE verificarMedico(
	IN cedula CHAR(10),
    IN psw VARCHAR(45)
	)
BEGIN
    SELECT M.idMedico
    FROM Personas P, Empleados E, Medicos M
    WHERE cedula=P.idPersona AND P.idPersona=E.idEmployee AND E.idEmpleado=M.idDoctor;
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE readPersona(
	IN cedula CHAR(10)
	)
BEGIN
    SELECT Pa.nroHistoria, Pe.Nombre, Pa.FechaNacimiento, Pa.Genero, Pa.EstadoCivil, Pa.GrupoSanguineo
    FROM Personas Pe, Pacientes Pa
    WHERE cedula=Pe.idPersona AND Pe.idPersona=Pa.idPatient;
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE readPersonaTelefonos(
	IN cedula CHAR(10)
	)
BEGIN
    SELECT Telefono
    FROM PersonasTelefonos 
    WHERE cedula=idPersonPhone;
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE readPersonaDomicilios(
	IN cedula CHAR(10)
	)
BEGIN
    SELECT Ciudad, Direccion
    FROM PersonasDomicilios
    WHERE cedula=idPersonHome;
END
//
DELIMITER ;



DELIMITER //
CREATE PROCEDURE readPersonaCorreos(
	IN cedula CHAR(10)
	)
BEGIN
    SELECT Correo
    FROM PersonasCorreos 
    WHERE cedula=idPersonEmail;
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE readPacienteOcupaciones(
	IN cedula CHAR(10)
	)
BEGIN
    SELECT Ocupacion
    FROM Pacientes Pa, PacientesOcupaciones Po
    WHERE cedula=Pa.idPatient AND Pa.nroHistoria=Po.idPatientWork;
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE obtenerNroHistoriaCedula(
	IN cedula CHAR(10)
	)
BEGIN
    SELECT Pa.nroHistoria
    FROM Personas P, Pacientes Pa 
    WHERE cedula=P.idPersona AND P.idPersona=Pa.idPatient;
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE obtenerNroHistoriaNombre(
	IN nombre VARCHAR(200)
	)
BEGIN
    SELECT Pa.nroHistoria
    FROM Personas P, Pacientes Pa 
    WHERE nombre=P.idPersona AND P.idPersona=Pa.idPatient;
END
//
DELIMITER ;



DELIMITER //
CREATE PROCEDURE obtenerIdPersonaNombre(
	IN nombre VARCHAR(200)
	)
BEGIN
    SELECT idPersona
    FROM Personas P 
    WHERE nombre=P.Nombre;
END
//
DELIMITER ;




DELIMITER //
CREATE PROCEDURE readNroHistoriaConsulta(
	IN nroHistoria INT
	)
BEGIN
    SELECT O.idOperacion, O.idPerson1, O.idPerson2, O.FechaHoraInicio, O.Tipo, C.idConsulta, C.FechaHoraFin, C.Motivos, C.ExamenFisico, C.Procedimiento, C.esEmergencia, C.Acompañante, C.Relacion
    FROM Operaciones O, ConsultasMedicas C
    WHERE nroHistoria=O.idPerson2 AND O.idOperacion=C.idMedicalVisit ;
END
//
DELIMITER ;
readFechaConsulta

DELIMITER //
CREATE PROCEDURE readFechaConsulta(
	IN fecha DATE
	)
BEGIN
    SELECT O.idOperacion, O.idPerson1, O.idPerson2, O.FechaHoraInicio, O.Tipo, C.idConsulta, C.FechaHoraFin, C.Motivos, C.ExamenFisico, C.Procedimiento, C.esEmergencia, C.Acompañante, C.Relacion
    FROM Operaciones O, ConsultasMedicas C
    WHERE O.idOperacion=C.idMedicalVisit AND date(O.FechaHoraInicio)=fecha order by O.FechaHoraInicio ASC;
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE readConsultaSignosVitales(
    IN idConsulta INT
    )
BEGIN
    SELECT idSignosVitales, Pulso, FrecuenciaRespiratoria, PresionSistolica, PresionDiastolica, SaturacionOxigeno, Temperatura, Talla, Peso
    FROM  SignosVitales 
    WHERE idConsulta=idVitalSigns;
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE readConsultaDiagnosticos(
    IN idConsulta INT
    )
BEGIN
    SELECT  Diagnostico, CIE10, TipoAntecedente
    FROM  Diagnosticos 
    WHERE idConsulta=idDiagnosis;
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE readConsultaCitas(
    IN idConsulta INT
    )
BEGIN
    SELECT Fecha, Descripcion
    FROM Citas 
    WHERE idConsulta=idCite;
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE readConsultaOrden(
    IN idConsulta INT
    )
BEGIN
    SELECT idOrden, FechaHoraAsistencia, Descripcion
    FROM  Ordenes
    WHERE idConsulta=idOrder;
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE readOrdenParametros(
    IN idOrden INT
    )
BEGIN
    SELECT P.Parametro
    FROM  OrdenesParametros Op, Parametros P
    WHERE idOrden=Op.idPrecepto AND Op.idIndicador=P.idParametro;
END
//
DELIMITER ;



DELIMITER //
CREATE PROCEDURE readConsultaTratamiento(
    IN idConsulta INT
    )
BEGIN
    SELECT idTratamiento, idSufferer, FechaInicio, FechaFin, Medicacion, Indicaciones
    FROM  Tratamientos 
    WHERE idConsulta=idTreatment;
END
//
DELIMITER ;



DELIMITER //
CREATE PROCEDURE readTratamientoHorarios(
    IN idTratamiento INT
    )
BEGIN
    SELECT idHorario, Hora, CondicionComida
    FROM    Horarios 
    WHERE idTratamiento=idSchedule ;
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE readHorariosAcciones(
    IN idHorario INT
    )
BEGIN
    SELECT idHorarioAccion, idActivity, Fecha, HoraEjecucion
    FROM  HorariosAcciones Ha
    WHERE idHorario=idAgenda;
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE readAccion(
    IN idActivity INT
    )
BEGIN
    SELECT idAccion, Tipo
    FROM  Acciones 
    WHERE idActivity=idAccion;
END
//
DELIMITER ;



DELIMITER //
CREATE PROCEDURE readAccionMedicionPA(
    IN idAccion INT
    )
BEGIN
    SELECT idMedicionPA, PresionSistolica, PresionDiastolica, Pulso
    FROM  MedicionesPA 
    WHERE idAccion=idMeasurePA;
END
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE readAccionMedicionGlucosa(
    IN idAccion INT
    )
BEGIN
    SELECT idMedicionGlucosa, Glucosa
    FROM  MedicionesGlucosa 
    WHERE idAccion=idMeasureGlucose;
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE readAccionAdmiMedicina(
    IN idAccion INT
    )
BEGIN
    SELECT idAdmiMedicina
    FROM  AdmiMedicinas Am
    WHERE idAccion=idAdmiMedicine;
END
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE readTomaMedicinaProductos(
    IN idTake INT
    )
BEGIN
    SELECT P.idProducto, Tm.Cantidad, P.NombreComercial, P.Presentacion, P.Laboratorio
    FROM  TomasMedicinas Tm, Productos P
    WHERE idTake=Tm.idTake AND Tm.idMedicamento=P.idProducto;
END
//
DELIMITER ;


#drop database MedicalCenterNarcisaDeJesus;

#SELECT * FROM AdmiMedicinas;
/*
   
SELECT  A.idAccion, A.Tipo, H.Hora, Ha.Fecha, Ha.HoraEjecucion

FROM HorariosAcciones Ha, Acciones A, Horarios H

WHERE A.idAccion=Ha.idActivity AND Ha.idAgenda=H.idHorario;


SELECT O.idOperacion, O.idPerson1, O.idPerson2, O.FechaHoraInicio, O.Tipo, C.idConsulta, C.FechaHoraFin, C.Motivos, C.ExamenFisico, C.Procedimiento, C.esEmergencia, C.Acompañante, C.Relacion
    FROM Operaciones O, ConsultasMedicas C
    WHERE O.idOperacion=C.idMedicalVisit AND date(O.FechaHoraInicio)='2018-12-29' order by O.FechaHoraInicio ASC;


*/

