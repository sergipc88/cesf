package com.SergiCesfBBDD;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner lector;

	// en el main hay un bucle while con las opciones a elegir al usuario,
	// luego la opcion elegida(opt) es enviada al metodo eleccion.
	public static void main(String[] args) {
		Main main = new Main();
		int opt;
		boolean serverON = false;
		lector = new Scanner(System.in);

		while (!serverON) {

			System.out.println("1.alta");
			System.out.println("2.baja");
			System.out.println("3.modificacion");
			System.out.println("4.consulta");
			System.out.println("5.comprobación");
			System.out.println(" ");
			System.out.println("elije opcion");
			opt = lector.nextInt();

			main.eleccion(opt);
		}

	}

	// en el metodo eleccion se hace una cosa u otra dependiendo lo que haya elegido
	// el usuario
	public void eleccion(int opt) {
		Mantenimiento man = new Mantenimiento();
		String nombre;
		int edad;
		String dni;
		lector = new Scanner(System.in);

		switch (opt) {

		case 1:// alta
			System.out.println("dar de alta usuario nuevo ...");
			System.out.println("introduce el dni");
			dni = lector.next();
			System.out.println("introduce el nombre");
			nombre = lector.next();
			System.out.println("introduce la edad");
			edad = lector.nextInt();
			System.out.println("insertando ...");		
			darDeAlta(nombre, dni, edad);
			System.out.println(" ");
			break;
		case 2:// baja
			System.out.println("introduce el DNI del alumno a eliminar");
			dni = lector.next();
			man.eliminar(dni);
			break;
		case 3:// modificacion
			System.out.println("inserta el DNI del alumnoa a modificar");
			dni = lector.next();
			System.out.println("introduce nombre y edad a modificar");
			nombre = lector.next();
			edad = lector.nextInt();
			man.modificar(dni, nombre, edad);
			break;
		case 4:// consulta
			System.out.println("Recuperando...");
			System.out.println(" ");
			List<Alumno> alumnos = man.consultar(); // recuperalos la lista
			recuperacion(alumnos); // se la pasamos al metodo recuperacion
			System.out.println(" ");
			break;
		case 5:// comprobacion
			System.out.println("introduce el dni");
			dni = lector.next();
			System.out.println("Comprobando...");
			man.comprobacion(dni);
		default:
			break;
		}

	}

	public void recuperacion(List<Alumno> datos) {
		// hacemos un iterator donde se iran imprimiendo todos los alumnos
		Iterator<Alumno> it = datos.iterator();
		while (it.hasNext()) {
			Alumno cadena = it.next();
			System.out.println(cadena.getNombre() + " " + cadena.getDni() + " " + cadena.getEdad());
		}
	}

	public void darDeAlta(String a, String b, int c) {
		Mantenimiento man = new Mantenimiento();

		if (man.insertar(a, b, c) == true) {

			System.out.println("el Alumno " + b + " ya exito");

		} else {
			System.out.println("Alumno " + a + " dado de alta con existe");
		}

	}

}
