package br.com.ticket.master.domain.model;

import br.com.ticket.master.domain.model.enums.MemberStatusEnum;
import br.com.ticket.master.domain.model.enums.OrganizationRoleEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProducerTeamMember {

    private UUID id;

    private UUID userId;

    private UUID producerId;

    private OrganizationRoleEnum organizationRole;

    private MemberStatusEnum status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private ProducerTeamMember(UUID id, UUID userId, UUID producerId, OrganizationRoleEnum organizationRole,
                               MemberStatusEnum status, LocalDateTime createdAt, LocalDateTime updatedAt,
                               LocalDateTime deletedAt) {
        this.id = id;
        this.userId = userId;
        this.producerId = producerId;
        this.organizationRole = organizationRole;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static ProducerTeamMember createOwner(UUID userId, UUID producerId) {
        return new ProducerTeamMember(null, userId, producerId, OrganizationRoleEnum.OWNER,
                MemberStatusEnum.APPROVED, null, null, null);
    }

    public static ProducerTeamMember invite(UUID userId, UUID producerId, OrganizationRoleEnum organizationRole) {
        initialValidation(userId, producerId, organizationRole);

        return new ProducerTeamMember(null, userId, producerId, organizationRole,
                MemberStatusEnum.PENDING, LocalDateTime.now(), null, null);
    }

    private static void initialValidation(UUID userId, UUID producerId, OrganizationRoleEnum organizationRole) {
        if(userId == null) {
            throw new IllegalArgumentException("User id is required.");
        }

        if(producerId == null) {
            throw new IllegalArgumentException("Producer id is required.");
        }

        if(organizationRole == null) {
            throw new IllegalArgumentException("Organization role is required.");
        }
    }


}
