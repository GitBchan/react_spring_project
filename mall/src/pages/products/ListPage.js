import ListComponents from "../../components/products/ListComponents";

const ListPage = () => {
    return (
        <div className="p-4 w-full bg-white">
            <div className="text-3xl font-extrabold">
                Products List Page
            </div>
            <ListComponents/>
        </div>
    );
}

export default ListPage;