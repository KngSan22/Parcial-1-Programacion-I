

static void main () {
    class Cliente {
        // Atributos
        private String nombreCompleto;
        private String nit;
        private int telefono;
        private String correo;
        private String pais;

        // Constructor
        public Cliente(String nombreCompleto, String nit, int telefono, String correo, String pais) {
            this.nombreCompleto = nombreCompleto;
            this.nit = nit;
            this.telefono = telefono;
            this.correo = correo;
            this.pais = pais;
        }

        // Getters y Setters
        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public String getNit() {
            return nit;
        }

        public void setNit(String nit) {
            this.nit = nit;
        }

        public int getTelefono() {
            return telefono;
        }

        public void setTelefono(int telefono) {
            this.telefono = telefono;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getPais() {
            return pais;
        }

        public void setPais(String pais) {
            this.pais = pais;
        }

        // Métodos del diagrama
        public void contratarProyecto() {
            System.out.println("El cliente " + nombreCompleto + " ha contratado un proyecto.");
        }

        public int consultarNumeroPerfecto(int numero) {
            int suma = 0;
            for (int i = 1; i < numero; i++) {
                if (numero % i == 0) {
                    suma += i;
                }
            }
            if (suma == numero) {
                System.out.println(numero + " es un número perfecto.");
                return numero;
            } else {
                System.out.println(numero + " no es un número perfecto.");
                return -1;
            }
        }
    }
}
