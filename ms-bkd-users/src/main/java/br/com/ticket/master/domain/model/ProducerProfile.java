package br.com.ticket.master.domain.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class ProducerProfile {

    private UUID id;

    private String companyName;

    private String tradingName;

    private String cnpj;

    private String contactEmail;

    private String contactPhone;

    private String bankCode;

    private String agency;

    private String accountNumber;

    private Set<ProducerTeamMember> teamMembers;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private ProducerProfile(UUID id, String companyName, String tradingName, String cnpj, String contactEmail,
                            String contactPhone, String bankCode, String agency, String accountNumber,
                            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.companyName = companyName;
        this.tradingName = tradingName;
        this.cnpj = cnpj;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.bankCode = bankCode;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static ProducerProfile createWithFounder(String companyName, String tradingName, String cnpj,
                                                    String contactEmail, String contactPhone, UUID founderUserId) {

        if (founderUserId == null) throw new IllegalArgumentException("Founder user id is required.");


        ProducerProfile profile = new ProducerProfile(null, companyName, tradingName, cnpj, contactEmail, contactPhone,
                null, null, null, null, null, null);


        profile.teamMembers.add(ProducerTeamMember.createOwner(founderUserId, profile.getId()));

        return profile;
    }

    public UUID getId(){
        return id;
    }


}
