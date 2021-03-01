package com.cotivity.ticket_reservation_authorization_server.mapper;

public interface Mapper<E,D> {

    E toEntity(D d);

}
