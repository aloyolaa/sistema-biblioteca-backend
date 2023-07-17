package com.republicaargentina.sistemabibliotecabackend.mapper;

import com.republicaargentina.sistemabibliotecabackend.dto.UsuarioDto;
import com.republicaargentina.sistemabibliotecabackend.entity.Rol;
import com.republicaargentina.sistemabibliotecabackend.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-16T20:26:37-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioDto usuarioDto) {
        if ( usuarioDto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( usuarioDto.id() );
        usuario.setUsername( usuarioDto.username() );
        usuario.setEmail( usuarioDto.email() );
        usuario.setNombres( usuarioDto.nombres() );
        usuario.setApellidos( usuarioDto.apellidos() );
        usuario.setHabilitado( usuarioDto.habilitado() );
        usuario.setRoles( rolDtoListToRolList( usuarioDto.roles() ) );

        return usuario;
    }

    @Override
    public UsuarioDto toDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String email = null;
        String nombres = null;
        String apellidos = null;
        Boolean habilitado = null;
        List<UsuarioDto.RolDto> roles = null;

        id = usuario.getId();
        username = usuario.getUsername();
        email = usuario.getEmail();
        nombres = usuario.getNombres();
        apellidos = usuario.getApellidos();
        habilitado = usuario.getHabilitado();
        roles = rolListToRolDtoList( usuario.getRoles() );

        UsuarioDto usuarioDto = new UsuarioDto( id, username, email, nombres, apellidos, habilitado, roles );

        return usuarioDto;
    }

    @Override
    public Usuario partialUpdate(UsuarioDto usuarioDto, Usuario usuario) {
        if ( usuarioDto == null ) {
            return usuario;
        }

        if ( usuarioDto.id() != null ) {
            usuario.setId( usuarioDto.id() );
        }
        if ( usuarioDto.username() != null ) {
            usuario.setUsername( usuarioDto.username() );
        }
        if ( usuarioDto.email() != null ) {
            usuario.setEmail( usuarioDto.email() );
        }
        if ( usuarioDto.nombres() != null ) {
            usuario.setNombres( usuarioDto.nombres() );
        }
        if ( usuarioDto.apellidos() != null ) {
            usuario.setApellidos( usuarioDto.apellidos() );
        }
        if ( usuarioDto.habilitado() != null ) {
            usuario.setHabilitado( usuarioDto.habilitado() );
        }
        if ( usuario.getRoles() != null ) {
            List<Rol> list = rolDtoListToRolList( usuarioDto.roles() );
            if ( list != null ) {
                usuario.getRoles().clear();
                usuario.getRoles().addAll( list );
            }
        }
        else {
            List<Rol> list = rolDtoListToRolList( usuarioDto.roles() );
            if ( list != null ) {
                usuario.setRoles( list );
            }
        }

        return usuario;
    }

    protected Rol rolDtoToRol(UsuarioDto.RolDto rolDto) {
        if ( rolDto == null ) {
            return null;
        }

        Rol rol = new Rol();

        rol.setId( rolDto.id() );
        rol.setNombre( rolDto.nombre() );

        return rol;
    }

    protected List<Rol> rolDtoListToRolList(List<UsuarioDto.RolDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Rol> list1 = new ArrayList<Rol>( list.size() );
        for ( UsuarioDto.RolDto rolDto : list ) {
            list1.add( rolDtoToRol( rolDto ) );
        }

        return list1;
    }

    protected UsuarioDto.RolDto rolToRolDto(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;

        id = rol.getId();
        nombre = rol.getNombre();

        UsuarioDto.RolDto rolDto = new UsuarioDto.RolDto( id, nombre );

        return rolDto;
    }

    protected List<UsuarioDto.RolDto> rolListToRolDtoList(List<Rol> list) {
        if ( list == null ) {
            return null;
        }

        List<UsuarioDto.RolDto> list1 = new ArrayList<UsuarioDto.RolDto>( list.size() );
        for ( Rol rol : list ) {
            list1.add( rolToRolDto( rol ) );
        }

        return list1;
    }
}
