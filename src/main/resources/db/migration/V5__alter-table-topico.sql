ALTER TABLE topico ADD curso_id BIGINT NOT NULL;
ALTER TABLE topico ADD CONSTRAINT fk_topico_curso_id
FOREIGN KEY (curso_id) REFERENCES curso(id);