package com.example.DatabaseInit.Entity;


import java.util.HashSet;
import java.util.Set;

import com.example.DatabaseInit.Enum.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "role_permissions",
		joinColumns = @JoinColumn(name="role_id"),
		inverseJoinColumns = @JoinColumn(name="permission_id")
		)
	private Set<Permission> permissions = new HashSet<>();

	public Role(Long id, ERole name) {
		this.id = id;
		this.name = name;
	}

	public Role(ERole name) {
		this.name = name;
	}
	
}
