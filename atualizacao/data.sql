-- Populando tabela de ReceitaQueijo 
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Saint Paulin');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Queijo Gouda');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Tomme - Leite Cru');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Morbier');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Brie/Camembert');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Morbier - Casca lavada com cachaça');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Queijo Gouda - Leite Orgânico');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Queijos diversos');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Queijo massa crua prensada');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Queijo Artesanal');
INSERT INTO receitaqueijo (nomeReceita) VALUES ('Queijo Reblochon');

-- Populando tabela Fermento 
INSERT INTO fermento (tipofermento, marca) VALUES ('Mesofilico old', 'Docina');
INSERT INTO fermento (tipofermento, marca) VALUES ('Mesofilico termofilico', 'Docina');
INSERT INTO fermento (tipofermento, marca) VALUES ('Mesofilico tipo O', 'Docina');
INSERT INTO fermento (tipofermento, marca) VALUES ('Mesofilico +PPR10', 'Docina+Lactopar');

-- Populando tabela Pessoa 
INSERT INTO pessoa (nome, endereco, telefone, tipoFiscal, documento, tipoPessoa) VALUES ('Beatriz Durlan', 'Rua Roxo nr 11','45 3333-3333','F', '00000000000','Fornecedor');  );
INSERT INTO pessoa (nome, endereco, telefone, tipoFiscal, documento, tipoPessoa) VALUES ('Cirlei Rossi', 'Rua Preto nr 22','45 3333-3333','F', '11111111111','Fornecedor');  );
INSERT INTO pessoa (nome, endereco, telefone, tipoFiscal, documento, tipoPessoa) VALUES ('Saul', 'Rua Verde nr 33','45 3333-3333','F', '22222222222','Fornecedor');  );
INSERT INTO pessoa (nome, endereco, telefone, tipoFiscal, documento, tipoPessoa) VALUES ('Lovani', 'Rua Amarelo nr 17','45 3333-3333','F', '33333333333','Fornecedor');  );
INSERT INTO pessoa (nome, endereco, telefone, tipoFiscal, documento, tipoPessoa) VALUES ('Gilvan', 'Rua Azul nr 98','45 3333-3333','F', '44444444444','Fornecedor');  );
INSERT INTO pessoa (nome, endereco, telefone, tipoFiscal, documento, tipoPessoa) VALUES ('Marlova Bombardelli', 'Rua Vermelho nr 20','45 3333-3333','F', '55555555555','Fornecedor');  );
INSERT INTO pessoa (nome, endereco, telefone, tipoFiscal, documento, tipoPessoa) VALUES ('Ronaldo', 'Rua Laranja nr 78','45 3333-3333','F', '66666666666','Fornecedor');  );
INSERT INTO pessoa (nome, endereco, telefone, tipoFiscal, documento, tipoPessoa) VALUES ('Maike T. M. Montanhini', 'Rua Branca nr 54','45 3333-3333','F', '77777777777','Funcionario');  );

-- Populando tabela Atributo 
INSERT INTO atributo (nomeAtributo) VALUES ('pH');
INSERT INTO atributo (nomeAtributo) VALUES ('Acidez');
INSERT INTO atributo (nomeAtributo) VALUES ('Gordura');
INSERT INTO atributo (nomeAtributo) VALUES ('Proteina');

-- Populando tabela Coleta 
INSERT INTO coletaleite (loteColeta,dtColeta,produtor_id,qtdLeite,funcionario_id, situacao) VALUES ('270619','2019-06-27',1,30,8,'Pago');
INSERT INTO coletaleite (loteColeta,dtColeta,produtor_id,qtdLeite,funcionario_id, situacao) VALUES ('0207196D','2019-07-02',1,30,8,'Pago');
INSERT INTO coletaleite (loteColeta,dtColeta,produtor_id,qtdLeite,funcionario_id, situacao) VALUES ('040719','2019-07-04',3,31,8,'Pendente');
INSERT INTO coletaleite (loteColeta,dtColeta,produtor_id,qtdLeite,funcionario_id, situacao) VALUES ('090719MB','2019-07-09',3,30,8,'Pago');
INSERT INTO coletaleite (loteColeta,dtColeta,produtor_id,qtdLeite,funcionario_id, situacao) VALUES ('110719','2019-07-11',1,30,8,'Pendente');

-- Populando tabela EntradaAtributo 
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('6,88',1,1);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('16',2,1);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('4,37',3,1);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('3,32',4,1);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('6,78',1,2);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('17',2,2);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('4,39',3,2);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('3,30',4,2);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('6,82',1,3);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('18',2,3);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('4,49',3,3);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('3,59',4,3);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('6,83',1,4);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('18',2,4);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('4,56',3,4);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('3,68',4,4);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('6,81',1,5);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('15',2,5);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('4,11',3,5);
INSERT INTO entradaatributo (valor, atributo_id, coleta_id) VALUES ('3,24',4,5);

-- Populando tabela FabricacaoQueijo 
INSERT INTO fabricacaoqueijo (receita_id, dataFabricacao, loteQueijo, coleta_id, qtdLeite, TipoProcessamento, tempoProcessamento, temperaturaProcessamento,temperaturaPreMaturacao, fermento_id, qtdFermento, tempoMaturacao, phMaturacao, temperaturaCoagulacao, qtdClCalcio, qtdCoagulante, tempoCoagulacao, phCorte,phEnformagem, phFinal, dessoragem, temperaturaDessoragem, tipoSalga, dataMaturacao, temperaturaMaturacao, dataLavagem, tipoTratamento, tempoTratamento, qtdPecas,pesoMPecas, observacoes) VALUES (1,'2019-06-27','270919SP',1,30,'Pasteurizado',30,63,36,1,1.5,60,6.81,0,14.4,21,40,6.87,0,0,0,0,'Salmora','2019-06-28',12,'2019-06-29','Outro',60,7,800,'Utilizado 10L de agua aquecida e retirado 15L de soro');
INSERT INTO fabricacaoqueijo (receita_id, dataFabricacao, loteQueijo, coleta_id, qtdLeite, TipoProcessamento, tempoProcessamento, temperaturaProcessamento,temperaturaPreMaturacao, fermento_id, qtdFermento, tempoMaturacao, phMaturacao, temperaturaCoagulacao, qtdClCalcio, qtdCoagulante, tempoCoagulacao, phCorte,phEnformagem, phFinal, dessoragem, temperaturaDessoragem, tipoSalga, dataMaturacao, temperaturaMaturacao, dataLavagem, tipoTratamento, tempoTratamento, qtdPecas,pesoMPecas, observacoes) VALUES (2,'2019-07-02','0207196D',2,30,'Pasteurizado',30,65,36,1,1.5,60,6.71,34,14.5,21,40,6.68,6.69,0,0,0,'Nenhuma','2019-07-03',12,'2019-07-03','Outro',60,1,3000,'');
INSERT INTO fabricacaoqueijo (receita_id, dataFabricacao, loteQueijo, coleta_id, qtdLeite, TipoProcessamento, tempoProcessamento, temperaturaProcessamento,temperaturaPreMaturacao, fermento_id, qtdFermento, tempoMaturacao, phMaturacao, temperaturaCoagulacao, qtdClCalcio, qtdCoagulante, tempoCoagulacao, phCorte,phEnformagem, phFinal, dessoragem, temperaturaDessoragem, tipoSalga, dataMaturacao, temperaturaMaturacao, dataLavagem, tipoTratamento, tempoTratamento, qtdPecas,pesoMPecas, observacoes) VALUES (3,'2019-07-04','040719TOMME',3,31,'Leite Cru',0,0,38,2,1.5,60,6.53,0,0,18,40,6.54,0,0,0,0,'Nenhuma','2019-06-28',12,'2019-07-05','Outro',60,1,3100,'Corte com faca, adição de sal na superfície, colocado na cerveja dia 05/07');
INSERT INTO fabricacaoqueijo (receita_id, dataFabricacao, loteQueijo, coleta_id, qtdLeite, TipoProcessamento, tempoProcessamento, temperaturaProcessamento,temperaturaPreMaturacao, fermento_id, qtdFermento, tempoMaturacao, phMaturacao, temperaturaCoagulacao, qtdClCalcio, qtdCoagulante, tempoCoagulacao, phCorte,phEnformagem, phFinal, dessoragem, temperaturaDessoragem, tipoSalga, dataMaturacao, temperaturaMaturacao, dataLavagem, tipoTratamento, tempoTratamento, qtdPecas,pesoMPecas, observacoes) VALUES (4,'2019-07-09','090719MB',3,30,'Pasteurizado',30,65,38,2,1.5,60,6.73,34,13.75,19.25,40,6.45,6.35,4.99,0,0,'Nenhuma','2019-07-10',12,'2019-07-11','Diário',60,3,980,'Utilização de leite de Jersey');
INSERT INTO fabricacaoqueijo (receita_id, dataFabricacao, loteQueijo, coleta_id, qtdLeite, TipoProcessamento, tempoProcessamento, temperaturaProcessamento,temperaturaPreMaturacao, fermento_id, qtdFermento, tempoMaturacao, phMaturacao, temperaturaCoagulacao, qtdClCalcio, qtdCoagulante, tempoCoagulacao, phCorte,phEnformagem, phFinal, dessoragem, temperaturaDessoragem, tipoSalga, dataMaturacao, temperaturaMaturacao, dataLavagem, tipoTratamento, tempoTratamento, qtdPecas,pesoMPecas, observacoes) VALUES (5,'2019-07-11','110719',1,30,'Pasteurizado',30,65,36,3,1.5,60,6.69,34,14.5,21,40,6.72,6.7,6.52,0,0,'Salmora','2019-07-12',14,'2019-07-12','Outro',60,12,491,'10 peças de 290g + 2 peças de 1,5kg');

       