package com.app.rys.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entidad Usuario
 * 
 * @author Naoufal
 *
 */
@Entity
@Table(name = "users")

public class User {

	// propiedaes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "my_entity_seq", initialValue = 1)
	private Long id;

	private String fullName;

	@NotNull
	@Email(message = "Email not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String email;

	//Validacion compartida con password y password confirm
	public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,16}$";
	public static final String PASSWORD_PATTERN_MESSAGE = "The password must have at least 6 and 16 characters, "
	        + "at least one digit, at least one lowercase and at least one uppercase. "
	        + "It can NOT have other symbols";

	@Pattern(message = PASSWORD_PATTERN_MESSAGE, regexp = PASSWORD_PATTERN)
	private String password;

	@Pattern(message = PASSWORD_PATTERN_MESSAGE, regexp = PASSWORD_PATTERN)
	private String confirmPassword;

	@Size(min = 9, max = 9, message = "The number phone must have 9 numbers")
	private String phone;

	// un usuario puede tener varias reservas @oneToMany
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookings;

	// Constructores: vacío y con campos
	public User(Long id, String fullName,
			@NotNull @Email(message = "Email not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}") String email,
			@Pattern(message = PASSWORD_PATTERN_MESSAGE, regexp = PASSWORD_PATTERN) String password,
			@Pattern(message = PASSWORD_PATTERN_MESSAGE, regexp = PASSWORD_PATTERN) String confirmPassword,
			@Size(min = 9, max = 9, message = "The number phone must have 9 numbers") String phone) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.phone = phone;
	}

	public User() {

	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	// equals & hashcode
	@Override
	public int hashCode() {
		return email.length() * 10 + fullName.length();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	// toString
	@Override
	public String toString() {
		return String.format("User [id=%s, fullName=%s, email=%s, password=%s, confirmPassword=%s, phone=%s]", id, fullName,
				email, password, confirmPassword, phone);
	}

	


}
