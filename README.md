Cahier des charges pour un miniCRM

Objectif du système :
Le système miniCRM vise à fournir une plateforme de gestion complète pour les entreprises, leur permettant de gérer efficacement leurs contacts, leurs interactions et leurs clients potentiels. L'objectif principal est d'optimiser les processus de vente et de marketing en centralisant les données et en fournissant des outils pour suivre et interagir avec les contacts tout au long du cycle de vie de la relation client.

Fonctionnalités principales :
Gestion des utilisateurs :
Créer, modifier et supprimer des comptes utilisateur.
Attribution de rôles et de permissions.
Authentification sécurisée.
Gestion des contacts :
Ajouter de nouveaux contacts avec des informations détaillées telles que nom, prénom, email, numéro de téléphone, etc.
Associer les contacts à des entreprises.
Suivre l'historique des interactions avec chaque contact.
Suivre le statut du cycle de vie du contact.
Gestion des entreprises :
Créer des profils d'entreprise avec des détails tels que le nom de l'entreprise, le logo, le secteur d'activité, la taille de l'entreprise, etc.
Associer des contacts à des entreprises.
Suivre les revenus et les performances des entreprises.
Gestion des interactions :
Enregistrer les interactions telles que les appels téléphoniques, les réunions, les courriels, les chats, etc.
Associer les interactions à des contacts et des entreprises.
Suivre les activités de suivi.
Gestion des clients potentiels (leads) :
Identifier et suivre les prospects qualifiés.
Convertir les prospects en clients lorsque nécessaire.
Suivre le parcours de conversion des leads.
Gestion des utilisateurs et des autorisations :
Définir des rôles utilisateur avec des permissions appropriées.
Limiter l'accès aux fonctionnalités en fonction des rôles.

Entity : 
	- User :{
		id, username , email , password , roles : Set<Role> , permissions : Set<Permission>
	}
	- RefreshToken :{
		id, user : User , token : String , expiryDate : Instant , loggedOut : booleen
	}
	- Role : {
		id, name : ERole , permissions : Set<Permission> 
	}
	- Permission : {
		id , name : EPermission
	}
	- Company : {
		id , companyName , CompanyLogo : byte[] , CompanySize : ECompanySize , totalIncome , industry : EIndustry , businessType : EBusinessType , country : ECountry , website , salesOwner : Contact , contacts :List<Contact> , CreatedAt , CreatedBy : User, timeZone : ETimeZone
	}
	- Contact : {
		id , firstName , lastName , email , phone , company : Company , CreatedAt , CreatedBy : User , interactions : List<Interaction> , lifecycleStage : List<ELifeCycleStage>
	}
	- Interaction : {
		id , type : EInteraction , notes , date , CreatedAt , contact : Contact , CreatedBy : User
	}
	- Customer : {
		this entity extend contact and its created only if lifecyclestage == won
	}
Enumeration : 
	- ERole {
	    ROLE_USER,
	    ROLE_MODERATOR,
	    ROLE_ADMIN,
	    ROLE_SALES_MANAGER,
	    ROLE_SALES_REPRESENTATIVE,
	    ROLE_SUPPORT_AGENT,
	    ROLE_MARKETING_MANAGER,
	    ROLE_MARKETING_SPECIALIST,
	    ROLE_ANALYTICS_MANAGER,
	    ROLE_SYSTEM_ADMINISTRATOR
	}
	- EPermission {
	    ACCESS_USER,
	    MODIFY_USER,
	    CREATE_USER,
	    DELETE_USER,
	    ACCESS_CONTACT,
	    MODIFY_CONTACT,
	    CREATE_CONTACT,
	    DELETE_CONTACT,
	    ACCESS_INTERACTION,
	    MODIFY_INTERACTION,
	    CREATE_INTERACTION,
	    DELETE_INTERACTION,
	}
	-EInteraction {
	    Phone_Calls,
	    Meetings,
	    Email_Correspondence,
	    Messages_Chats,
	    Social_Media_Interaction,
	    Events_Attendance,
	    Sales_Interactions,
	    Support_Services_Requests,
	    Surveys_Feedback
	}
	- ETimeZone {
	    UTC,
	    GMT,
	    UST,
	    CET,
	    EET,
	    ART,
	    AST,
	    EST,
	    CST,
	    MST,
	    PST,
	    AKST,
	    HST,
	    AEST,
	    ACST,
	    AWST,
	    JST,
	    KST,
	    IST,
	    NST
	}
	- EIndustry {
	    AEROSPACE,
	    AGRICULTURE,
	    AUTOMOTIVE,
	    CHEMICALS,
	    CONSTRUCTION,
	    DEFENSE,
	    EDUCATION,
	    ENERGY,
	    FINANCIAL_SERVICES,
	    FOOD_AND_BEVERAGE,
	    HEALTHCARE,
	    HOSPITALITY,
	    INFORMATION_TECHNOLOGY,
	    MANUFACTURING,
	    MEDIA_AND_ENTERTAINMENT,
	    PHARMACEUTICALS,
	    RETAIL,
	    TELECOMMUNICATIONS,
	    TRANSPORTATION,
	    UTILITIES,
	    REAL_ESTATE,
	    SPORTS,
	    LOGISTICS,
	    CONSULTING,
	    ENVIRONMENTAL,
	    RESEARCH,
	    BIOTECHNOLOGY,
	    TEXTILES,
	    INSURANCE,
	    OTHER
	}
	- ECountry {
	    AFGHANISTAN,
	    ALBANIA,
	    ALGERIA,
	    .... etc
	}
	- ECompanySize {
	    ENTREPRISE,
	    LARGE,
	    MEDIUM,
	    SMALL
	}
	- EBusinessType {
	    B2G,
	    B2C,
	    B2B
	}
	
	
