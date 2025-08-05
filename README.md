public class BookDAO {
    private EntityManager entityManager;

    public BookDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Book> findByAuthorAndPrice(String author, double price) {
        // ایجاد یک CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // ایجاد یک CriteriaQuery
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        // تعریف ریشه (Root)
        Root<Book> root = criteriaQuery.from(Book.class);

        // اضافه کردن شرایط به کوئری
        criteriaQuery.select(root)
                     .where(criteriaBuilder.and(
                         criteriaBuilder.equal(root.get("author"), author),
                         criteriaBuilder.lessThan(root.get("price"), price)
                     ));

        // اجرای کوئری و دریافت نتایج
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
