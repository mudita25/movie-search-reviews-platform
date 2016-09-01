package com.mudita.movies.repository.impl;

import com.mudita.movies.entity.Theatre;
import com.mudita.movies.entity.impl.MovieImpl;
import com.mudita.movies.entity.impl.TheatreImpl;
import com.mudita.movies.http.utils.StringUtils;
import com.mudita.movies.repository.TheatreRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Set;


@Repository
public class TheatreRepositoryImpl implements TheatreRepository{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Theatre> getTheatreById(Set<Long> theatreIds){
        List<Theatre> theatres = null;

        Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheatreImpl.class).
                add(Restrictions.in("theatreId", theatreIds));
        theatres = crit.list();

        if(theatres == null || theatres.isEmpty()){
            return null;
        }
        return theatres;
    }

    @Override
    public List<Theatre> getAllTheatres(int limit, int offset) {
        Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheatreImpl.class);
        crit.setFirstResult((offset) * limit);
        crit.setMaxResults(limit);
        List<Theatre> theatres = crit.list();
        return theatres;
    }

    @Override
    public List<Theatre> getTheatresByParameters(String name, int zip, String place, int limit, int offset) {
        Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheatreImpl.class);
        if(!StringUtils.isNullOrEmpty(name)){
            crit.add(Restrictions.like("theatreName","%" + name + "%"));
        }
        if(!StringUtils.isNull(zip)){
            crit.add(Restrictions.eq("zip",zip));
        }
        if(!StringUtils.isNullOrEmpty(place)){
            crit.add(Restrictions.like("city","%" + place + "%"));
        }
        crit.setFirstResult((offset) * limit);
        crit.setMaxResults(limit);
        List<Theatre> theatres = crit.list();
        return theatres;
    }

    @Override
    public Theatre getTheatreByName(String theatreName){
        Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheatreImpl.class).add(Restrictions.like("theatreName","%" + theatreName + "%"));
        List<Theatre> theatres = crit.list();
        if(theatres == null || theatres.isEmpty())
            return null;
        return theatres.iterator().next();
    }
}
