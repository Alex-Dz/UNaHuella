package com.unal.una_huella.UNaHuellaLauncher.ED;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HashTable {

    private Mascota[] array;
    private long count;
    private ArrayList<Long> primos;
    private double factorCarga;
    private int colider;

    public HashTable() {
        this.array = new Mascota[10007];
        this.count = 0;
        this.primos = null;
        this.factorCarga = 0;
        this.colider = 0;
    }

    private long getPrime(long numero, int moM) {
        if (primos == null) {
            this.primos = new ArrayList<Long>();
            BufferedReader br;
            File file = new File("src/main/resources/static/primos.txt");
            try {
                FileReader fr = new FileReader(file);
                br = new BufferedReader(fr);
                for (int i = 0; i < 100000; i++) {
                    primos.add(Long.parseLong(br.readLine()));
                }
                br.close();
            } catch (FileNotFoundException e) {
                System.err.println("archivo de primos no encontrado o no disponible");
            } catch (IOException e) {
                System.err.println("no se pudo leer el valor del archivo");
            }
        }
        if (moM == -1) {
            for (int i = 0; i < primos.size(); i++) {
                if (primos.get(i) == numero) {
                    return primos.get(i - 1);
                }
            }
        } else if (moM == 1) {
            for (int i = 0; i < primos.size(); i++) {
                if (primos.get(i) > numero) {
                    return primos.get(i);
                }
            }
        }
        return -1;
    }

    private long h1(long x, long N) {
        long hash = x % N;
        return (hash + f(x, N)) % N;
    }

    private long h2(long x, long N) {
        long R = getPrime(N, -1);
        return R - (x % R);
    }

    private long f(long x, long N) {
        return colider * h2(x, N);
    }

    private double getCarga() {
        factorCarga = (double) count / (double) array.length;
        return factorCarga;
    }

    public void insert(Mascota mascota) {
        colider = 0;
        long id = Long.parseLong(mascota.getI_id_dueño().getId_usuario());
        long hash = id % array.length;
        long pos = hash;
        if (factorCarga < 0.85) {
            if (array[(int) pos] == null || array[(int) pos].getI_id_dueño() == null) {
                array[(int) pos] = mascota;
                count++;
                getCarga();
            } else {
                colider++;
                pos = h1(id, array.length);
                while (array[(int) pos] != null) {
                    if (array[(int) pos] != null && array[(int) pos].getI_id_dueño() == null) {
                        break;
                    }
                    colider++;
                    pos = h1(id, array.length);
                }
                array[(int) pos] = mascota;
                pos = 0;
                count++;
                getCarga();
                colider = 0;
            }
        } else {
            rehashing();
            insert(mascota);
        }
    }

    public Mascota find(String idDueño, long idMascota) {
        colider = 0;
        long id = Long.parseLong(idDueño);
        long hash = id % array.length;
        long pos = hash;
        if (array[(int) pos] != null
                && array[(int) pos].getI_id_dueño() != null
                && array[(int) pos].getI_id_dueño().getId_usuario().equals(idDueño)
                && array[(int) pos].getId_mascota() == idMascota) {
            return array[(int) pos];
        } else {
            colider++;
            pos = h1(id, array.length);
            while (array[(int) pos] != null) {
                if (array[(int) pos] != null
                        && array[(int) pos].getI_id_dueño() != null
                        && array[(int) pos].getI_id_dueño().getId_usuario().equals(idDueño)
                        && array[(int) pos].getId_mascota() == idMascota) {
                    break;
                }
                colider++;
                pos = h1(id, array.length);
            }
            if (array[(int) pos] == null) {
                return null;
            }
            colider = 0;
            return array[(int) pos];
        }
    }

    public List<Mascota> findAll(String idDueño) {
        colider = 0;
        List<Mascota> mascotas = new ArrayList<Mascota>();
        long id = Long.parseLong(idDueño);
        long hash = id % array.length;
        long pos = hash;
        if (array[(int) hash] == null) {
            return null;
        } else {
            while (array[(int) pos] != null) {
                if (array[(int) pos].getI_id_dueño() != null && array[(int) pos].getI_id_dueño().getId_usuario().equals(idDueño)) {
                    mascotas.add(array[(int) pos]);
                }
                colider++;
                pos = h1(id, array.length);
            }
            pos = 0;
            colider = 0;
            if (mascotas.size() == 0) {
                return null;
            }
            return mascotas;
        }
    }

    public Mascota delete(Mascota mascota) {
        colider = 0;
        Mascota mascoto = new Mascota();
        mascoto.setId_mascota(mascota.getId_mascota());
        mascoto.setA_especie(mascota.getA_especie());
        mascoto.setB_nombre_mascota(mascota.getB_nombre_mascota());
        mascoto.setC_genero(mascota.getC_genero());
        mascoto.setD_raza(mascota.getD_raza());
        mascoto.setE_edad_mascota(mascota.getE_edad_mascota());
        mascoto.setF_historial_cirugias(mascota.getF_historial_cirugias());
        mascoto.setG_portador_parasito(mascota.getG_portador_parasito());
        mascoto.setH_carnet_vacunacion(mascota.getH_carnet_vacunacion());
        mascoto.setI_id_dueño(mascota.getI_id_dueño());
        mascoto.setCitasMascota(mascota.getCitasMascota());
        long id = Long.parseLong(mascota.getI_id_dueño().getId_usuario());
        long hash = id % array.length;
        long pos = hash;
        if (array[(int) pos] != null
                && array[(int) pos].getI_id_dueño().getId_usuario().equals(mascota.getI_id_dueño().getId_usuario())
                && array[(int) pos].getId_mascota() == mascota.getId_mascota()) {
            array[(int) pos].setI_id_dueño(null);
            array[(int) pos].setId_mascota(-1);
            array[(int) pos].setA_especie("");
            array[(int) pos].setB_nombre_mascota("");
            array[(int) pos].setC_genero("");
            array[(int) pos].setD_raza("");
            array[(int) pos].setE_edad_mascota(0);
            array[(int) pos].setF_historial_cirugias("");
            array[(int) pos].setG_portador_parasito("");
            array[(int) pos].setH_carnet_vacunacion(null);
            count--;
            getCarga();
            return mascoto;
        } else {
            do {
                colider++;
                pos = h1(id, array.length);
                if (array[(int) pos] != null
                        && array[(int) pos].getI_id_dueño() != null
                        && array[(int) pos].getI_id_dueño().getId_usuario().equals(mascota.getI_id_dueño().getId_usuario())
                        && array[(int) pos].getId_mascota() == mascota.getId_mascota()) {
                    break;
                }
            } while (array[(int) pos] != null);

            if (array[(int) pos] != null) {
                array[(int) pos].setI_id_dueño(null);
                array[(int) pos].setId_mascota(-1);
                array[(int) pos].setA_especie("");
                array[(int) pos].setB_nombre_mascota("");
                array[(int) pos].setC_genero("");
                array[(int) pos].setD_raza("");
                array[(int) pos].setE_edad_mascota(0);
                array[(int) pos].setF_historial_cirugias("");
                array[(int) pos].setG_portador_parasito("");
                array[(int) pos].setH_carnet_vacunacion(null);
                pos = 0;
                count--;
                colider = 0;
                getCarga();
                return mascoto;
            }
            return null;
        }
    }

    public void rehashing() {
        long N = 2 * array.length;
        N = getPrime(N, 1);
        Mascota[] temp = new Mascota[(int) N];
        factorCarga = 0;
        long tcount = 0;
        for (long i = 0; i < array.length; i++) {
            if (array[(int) i] != null
                    && array[(int) i].getI_id_dueño() != null) {
                colider = 0;
                long id = Long.parseLong(array[(int) i].getI_id_dueño().getId_usuario());
                long hash = id % temp.length;
                long pos = hash;
                if (temp[(int) pos] == null) {
                    temp[(int) pos] = array[(int) i];
                    tcount++;
                    factorCarga = (double) tcount / (double) temp.length;
                } else {
                    do {
                        colider++;
                        pos = h1(id, N);
                        if (temp[(int) pos] == null || temp[(int) pos].getI_id_dueño() == null) {
                            break;
                        }
                    } while (temp[(int) pos] != null);
                    temp[(int) pos] = array[(int) i];
                    pos = 0;
                    tcount++;
                    factorCarga = (double) tcount / (double) temp.length;
                    colider = 0;
                }

            }
        }
        count = tcount;
        array = temp;
    }

}

